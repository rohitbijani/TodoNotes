package com.fundooNotes.labels.service;

import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundooNotes.exception.NoteException;
import com.fundooNotes.exception.UserNotFoundException;
import com.fundooNotes.labels.dao.LabelDao;
import com.fundooNotes.labels.model.CreateLabelDto;
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
	public void createLabel(CreateLabelDto createLabelDto, String token) {
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		if(user==null) {
			throw new UserNotFoundException("Label not created. User not found!");
		}
		
		Label label=modelMapper.map(createLabelDto, Label.class);
		label.setUser(user);
		labelDao.save(label);
	}

	@Override
	public void deleteLabel(LabelDto labelDto, String token) {
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		if(user==null) {
			throw new UserNotFoundException("User doesn't exists!");
		}
		
		Label label=labelDao.getLabelById(labelDto.getId());
		if (label==null || (label.getUser().getId()!=userId)) {
			throw new NoteException("Cannot delete. Label not found!");
		}
		
		labelDao.delete(label);
	}

	@Override
	public void updateLabel(LabelDto labelDto, String token) {
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		if(user==null) {
			throw new UserNotFoundException("User doesn't exists!");
		}
		
		Label label=labelDao.getLabelById(labelDto.getId());
		if (label==null || (label.getUser().getId()!=userId)) {
			throw new NoteException("Cannot delete. Label not found!");
		}
		
		modelMapper.map(labelDto, label);
		labelDao.update(label);
	}

	@Override
	public List<Label> getLabels(String token) {
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		if(user==null) {
			throw new UserNotFoundException("Cannot get notes. User doesn't exists!");
		}
		
		List<Label> labels=labelDao.getLabels(user);
		Collections.reverse(labels);
		return labels;
	}

}
