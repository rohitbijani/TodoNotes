package com.fundooNotes.labels.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundooNotes.exception.UserNotFoundException;
import com.fundooNotes.labels.dao.LabelDao;
import com.fundooNotes.labels.model.Label;
import com.fundooNotes.labels.model.LabelDto;
import com.fundooNotes.user.dao.UserDao;
import com.fundooNotes.user.model.User;
import com.fundooNotes.util.TokenGenerator;

@Service
@Transactional
public class LabelServiceImpl implements LabelService {
	@Autowired
	UserDao userDao;
	@Autowired
	LabelService labelService;
	@Autowired
	LabelDao labelDao;
	@Autowired
	TokenGenerator tokenGenerator;
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public void createLabel(LabelDto labelDto, String token) {
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		if(user==null) {
			throw new UserNotFoundException("Label not created. User not found!");
		}
		
		Label label=modelMapper.map(labelDto, Label.class);
		label.setUser(user);
		labelDao.save(label);
	}

	@Override
	public void deleteLabel(LabelDto labelDto, String token) {
		
	}

	@Override
	public void updateLabel(LabelDto labelDto, String token) {
		
		
	}

	@Override
	public List<Label> getLabels(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
