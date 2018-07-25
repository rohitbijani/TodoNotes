package com.fundooNotes.labels.model;

import java.util.List;

import com.fundooNotes.notes.model.Note;

public class LabelDto {
	private int id;
	private String name;
	private List<Note> notes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
}
