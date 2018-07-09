package com.fundooNotes.user.service;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundooNotes.exception.RegistrationException;
import com.fundooNotes.exception.JwtException;
import com.fundooNotes.exception.UserNotFoundException;
import com.fundooNotes.user.dao.UserDao;
import com.fundooNotes.user.model.User;
import com.fundooNotes.util.EmailUtil;
import com.fundooNotes.util.RedisUtil;
import com.fundooNotes.util.TokenGenerator;

import io.lettuce.core.RedisException;

import com.fundooNotes.user.model.LoginDto;
import com.fundooNotes.user.model.RegistrationDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	TokenGenerator tokenGenerator;
	@Autowired
	EmailUtil emailUtil;
	@Autowired
	RedisUtil redisUtil;
	
	@Override
	@Transactional
	public void registerUser(RegistrationDto registrationDto, HttpServletRequest request) {
		User user=modelMapper.map(registrationDto, User.class);
		User userInfo=userDao.getUserByEmail(user.getEmail());		
		if (userInfo!=null) {
			throw new RegistrationException("Email ID already registered");
		}

		String encrypted=BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(15));
		user.setPassword(encrypted);
		Integer id=userDao.save(user);
		if (id==null) {
			throw new RegistrationException("Database save error");
		}

		try {
			String token=tokenGenerator.createJWT(id.toString(), "RohitBijani", user.getEmail(), 3600000);
			String link=request.getScheme()+"://192.168.0.55:"+request.getServerPort()+
					request.getRequestURI().replaceAll("registration", "verification")+"/"+token;
			redisUtil.syncRedis().set(id.toString(), token);
			System.out.println(link);
			emailUtil.sendEmail("FundooNotes", user.getEmail(), "Email Verification", link);	
			
		} catch (RedisException | MailException e) {
			throw new RegistrationException("Registration failed!");
		}
	}

	@Override
	@Transactional
	public String loginUser(LoginDto loginDto) {
		User userInfo=userDao.getUserByEmail(loginDto.getEmail());
		if(userInfo==null) {
			throw new UserNotFoundException("Login Error: User doesn't exists!");
		}

		String password=userInfo.getPassword();
		if(!BCrypt.checkpw(loginDto.getPassword(), password)) {
			throw new UserNotFoundException("Login Error: Invalid Password!");
		}

		Integer id=userInfo.getId();
		String token=tokenGenerator.createJWT(id.toString(), "RohitBijani", userInfo.getEmail(), 36000000);
		System.out.println("Login token: "+token);
		return token;
	}

	@Override
	@Transactional
	public void verifyUser(String jwt) {
		Integer id=tokenGenerator.parseJWT(jwt);
		String redisToken=redisUtil.syncRedis().get(id.toString());
		if (!redisToken.equals(jwt)) {
			throw new JwtException("JWT signature does not match.");
		}
		
		User userInfo=userDao.getUserById(id);
		userInfo.setVerified(true);
		
		id=userDao.save(userInfo);
		if (id==null) {
			throw new RegistrationException("Database save error");
		}
		redisUtil.syncRedis().flushdb();	
	}

	@Override
	@Transactional
	public void forgotPassword(String email, HttpServletRequest request) {
		User userInfo=userDao.getUserByEmail(email);
		if (userInfo==null) {
			throw new UserNotFoundException("User doesn't exists!");
		}
		
		try {
			Integer id = userInfo.getId();
			String token=tokenGenerator.createJWT(id.toString(), "RohitBijani", userInfo.getEmail(), 3600000);
			redisUtil.syncRedis().set(id.toString(), token);
			String link=request.getScheme()+"://192.168.0.55:"+request.getServerPort()+"/notes/reset-password/"+token;
			System.out.println(link);
			emailUtil.sendEmail("fundoo8080@gmail.com", userInfo.getEmail(), "Reset Password", link);
			
		} catch (RedisException | MailException e) {
			throw new RegistrationException("!");
		}			
	}

	@Override
	@Transactional
	public void resetPassword(String jwt, String password) {
		Integer id=tokenGenerator.parseJWT(jwt);
		String redisToken=redisUtil.syncRedis().get(id.toString());
		if (!redisToken.equals(jwt)) {
			throw new JwtException("JWT signature does not match.");
		}
		
		User userInfo=userDao.getUserById(id);
		String encrypted=BCrypt.hashpw(userInfo.getPassword(), BCrypt.gensalt(15));
		userInfo.setPassword(encrypted);
		
		id=userDao.save(userInfo);
		if (id==null) {
			throw new RegistrationException("Database save error");
		}
		redisUtil.syncRedis().flushdb();
	}

}
