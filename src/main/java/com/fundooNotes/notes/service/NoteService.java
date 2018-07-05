package com.fundooNotes.notes.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fundooNotes.notes.model.CreateNoteDto;
import com.fundooNotes.notes.model.Note;

public interface NoteService {
	public void createNote(CreateNoteDto createNoteDto, HttpServletRequest request);
	public void deleteNote(HttpServletRequest request);
	public void updateNote(Note note, HttpServletRequest request);
	public List<Note> getNotes(HttpServletRequest request);

}
