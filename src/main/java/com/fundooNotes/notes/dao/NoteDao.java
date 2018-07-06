package com.fundooNotes.notes.dao;

import java.util.List;

import com.fundooNotes.notes.model.Note;

public interface NoteDao {
	
	public Integer save(Note note);
	public void update(Note note);
	public Note getNoteById(Integer id);
	public List<Note> getNotes(Integer userId);

}
