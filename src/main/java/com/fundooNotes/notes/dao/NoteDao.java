package com.fundooNotes.notes.dao;

import java.util.List;

import com.fundooNotes.labels.model.Label;
import com.fundooNotes.notes.model.Note;
import com.fundooNotes.user.model.User;

public interface NoteDao {
	
	public Integer save(Note note);
	public void update(Note note);
	public void delete(Note note);
	public Note getNoteById(Integer id);
	public List<Note> getNotes(User user);
	public List<Note> getLabelNotes(Label label);

}
