package com.fundooNotes.notes.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundooNotes.exception.NoteException;
import com.fundooNotes.exception.UserNotFoundException;
import com.fundooNotes.notes.dao.NoteDao;
import com.fundooNotes.notes.model.CreateNoteDto;
import com.fundooNotes.notes.model.Note;
import com.fundooNotes.notes.model.UpdateNoteDto;
import com.fundooNotes.user.dao.UserDao;
import com.fundooNotes.user.model.User;
import com.fundooNotes.util.TokenGenerator;

@Service
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	UserDao userDao;
	@Autowired
	NoteDao noteDao;
	@Autowired
	TokenGenerator tokenGenerator;
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	@Transactional
	public void createNote(CreateNoteDto createNoteDto, String token) {
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		if(user==null) {
			throw new UserNotFoundException("Note not created. User not found!");
		}
		
		Note note=modelMapper.map(createNoteDto, Note.class);
		note.setUser(user);
		note.setCreatedDate(new Date());
		note.setEditedDate(new Date());
		noteDao.save(note);
	}

	@Override
	@Transactional
	public void deleteNote(Integer id, String token) {
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		if(user==null) {
			throw new UserNotFoundException("User doesn't exists!");
		}
		
		Note note=noteDao.getNoteById(id);
		if(note==null || (note.getUser().getId()!=userId)) {
			throw new NoteException("Cannot delete. Note not found!");
		}
		
		noteDao.delete(note);
	}

	@Override
	@Transactional
	public void updateNote(UpdateNoteDto updateNoteDto, String token) {
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		if(user==null) {
			throw new UserNotFoundException("User doesn't exists!");
		}
		
		Note note=noteDao.getNoteById(updateNoteDto.getId());
		if(note==null || (note.getUser().getId()!=userId)) {
			throw new NoteException("Cannot update. Note not found!");
		}
		
		if(! updateNoteDto.getTitle().equals(note.getTitle()) || 
				! updateNoteDto.getDescription().equals(note.getDescription())) {
			note.setEditedDate(new Date());
		}
		
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.map(updateNoteDto, note);
		System.out.println(note.getId());
		noteDao.update(note);
	}

	@Override
	@Transactional
	public List<Note> getNotes(String token) {
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		if(user==null) {
			throw new UserNotFoundException("Cannot get notes. User doesn't exists!");
		}
		
		List<Note> notes=noteDao.getNotes(user);
		Collections.reverse(notes);
		return notes;
	}

}
