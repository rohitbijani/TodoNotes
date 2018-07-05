package com.fundooNotes.notes.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fundooNotes.notes.model.CreateNoteDto;
import com.fundooNotes.notes.model.Note;

@Service
public class NoteServiceImpl implements NoteService {

	@Override
	public void createNote(CreateNoteDto createNoteDto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNote(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNote(Note note, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Note> getNotes(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}


}
