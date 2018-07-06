package com.fundooNotes.notes.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fundooNotes.exception.UserNotFoundException;
import com.fundooNotes.notes.model.CreateNoteDto;
import com.fundooNotes.notes.model.Note;
import com.fundooNotes.notes.model.UpdateNoteDto;

public interface NoteService {
	public void createNote(CreateNoteDto createNoteDto, HttpServletRequest request) throws UserNotFoundException;
	public void deleteNote(Integer id, HttpServletRequest request);
	public void updateNote(Integer id, UpdateNoteDto updateNoteDto, HttpServletRequest request);
	public List<Note> getNotes(HttpServletRequest request);

}
