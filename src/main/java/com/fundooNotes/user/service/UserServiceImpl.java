package com.fundooNotes.user.service;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundooNotes.configuration.EmailUtil;
import com.fundooNotes.configuration.RedisUtil;
import com.fundooNotes.configuration.TokenGenerator;
import com.fundooNotes.user.dao.UserDao;
import com.fundooNotes.user.model.User;

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
	public Integer registerUser(RegistrationDto registrationDto, HttpServletRequest request) {
		User user=modelMapper.map(registrationDto, User.class);
		User userInfo=userDao.getUserByEmail(user.getEmail());
		Integer id = null;
		
		if (userInfo==null) {
			String encrypted=BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(15));
			user.setPassword(encrypted);
			id=userDao.save(user);
			if (id!=null) {
				String token=tokenGenerator.createJWT(id.toString(), "RohitBijani", user.getEmail(), 3600000);
				String link=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+
						request.getRequestURI().replaceAll("registration", "verification")+"/"+token;
				redisUtil.syncRedis().set(id.toString(), token);
				System.out.println(link);
				emailUtil.sendEmail("fundoo8080@gmail.com", user.getEmail(), "Email Verification", link);
			}
		}
		return id;
	}

	@Override
	@Transactional
	public User loginUser(LoginDto loginDto) {
		User userInfo=userDao.getUserByEmail(loginDto.getEmail());
		
		if (userInfo!=null) {
			String password=userInfo.getPassword();
			if(BCrypt.checkpw(loginDto.getPassword(), password)) {
				return userInfo;
			}		
		}		
		return null;
	}

	@Override
	@Transactional
	public Integer verifyUser(String jwt) {
		Integer id=tokenGenerator.parseJWT(jwt);
		String redisToken=redisUtil.syncRedis().get(id.toString());
		
		if (redisToken.equals(jwt)) {
			User userInfo=userDao.getUserById(id);
			userInfo.setVerified(true);
			id=userDao.save(userInfo);
		}
		return id;
	}

	@Override
	@Transactional
	public Integer forgotPassword(String email, HttpServletRequest request) {
		User userInfo=userDao.getUserByEmail(email);
		Integer id = null;
		
		if (userInfo!=null) {
			id = userInfo.getId();
			String token=tokenGenerator.createJWT(id.toString(), "RohitBijani", userInfo.getEmail(), 3600000);
			redisUtil.syncRedis().set(id.toString(), token);
			String link=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/notes/reset-password/"+token;
			System.out.println(link);
			emailUtil.sendEmail("fundoo8080@gmail.com", userInfo.getEmail(), "Reset Password", link);
		}
		return id;
	}

	@Override
	@Transactional
	public Integer resetPassword(String jwt, String password) {
		Integer id=tokenGenerator.parseJWT(jwt);
		String redisToken=redisUtil.syncRedis().get(id.toString());

		if (redisToken.equals(jwt)) {
			User userInfo=userDao.getUserById(id);
			String encrypted=BCrypt.hashpw(userInfo.getPassword(), BCrypt.gensalt(15));
			userInfo.setPassword(encrypted);
			id=userDao.save(userInfo);
		}
		return id;
	}

}
