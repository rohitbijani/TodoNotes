package com.fundooNotes.notes.service;

import java.util.List;

import com.fundooNotes.notes.model.CreateNoteDto;
import com.fundooNotes.notes.model.Note;
import com.fundooNotes.notes.model.UpdateNoteDto;

public interface NoteService {
	public void createNote(CreateNoteDto createNoteDto, String token);
	public void deleteNote(Integer id, String token);
	public void updateNote(UpdateNoteDto updateNoteDto, String token);
	public List<Note> getNotes(String token);

}
