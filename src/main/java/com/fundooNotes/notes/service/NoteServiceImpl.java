package com.fundooNotes.notes.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundooNotes.configuration.TokenGenerator;
import com.fundooNotes.exception.UserNotFoundException;
import com.fundooNotes.notes.dao.NoteDao;
import com.fundooNotes.notes.model.CreateNoteDto;
import com.fundooNotes.notes.model.Note;
import com.fundooNotes.notes.model.UpdateNoteDto;
import com.fundooNotes.user.dao.UserDao;
import com.fundooNotes.user.model.User;

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
	public void createNote(CreateNoteDto createNoteDto, HttpServletRequest request) {
		String token=request.getHeader("uid");
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		/*if(user==null) {
			throw new UserNotFoundException();
		}*/
		
		Note note=modelMapper.map(createNoteDto, Note.class);
		note.setUser(user);
		note.setCreatedDate(new Date());
		note.setEditedDate(new Date());
		userId=noteDao.save(note);
		
		if (userId==null) {
			throw new HibernateException("Could not create the Note!");
		}
	}

	@Override
	@Transactional
	public void deleteNote(Integer id, HttpServletRequest request) {
		String token=request.getHeader("uid");
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		if(user==null) {
			throw new UserNotFoundException();
		}
		
		Note note=noteDao.getNoteById(id);
		/*if(note==null) {
			throw new Exception();
		}*/
		
		noteDao.delete(note);
	}

	@Override
	@Transactional
	public void updateNote(Integer id, UpdateNoteDto updateNoteDto, HttpServletRequest request) {
		String token=request.getHeader("uid");
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
		/*if(user==null) {
			throw new UserNotFoundException();
		}
		*/
		Note note=noteDao.getNoteById(id);
		note=modelMapper.map(updateNoteDto, Note.class);
		note.setEditedDate(new Date());
		noteDao.update(note);
	}

	@Override
	@Transactional
	public List<Note> getNotes(HttpServletRequest request) {
		String token=request.getHeader("uid");
		Integer userId=tokenGenerator.parseJWT(token);
		User user=userDao.getUserById(userId);
		
/*		if(user==null) {
			throw new UserNotFoundException();
		}
*/		
		List<Note> notes=noteDao.getNotes(userId);
		return notes;
	}

}
