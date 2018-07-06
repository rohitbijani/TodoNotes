package com.fundooNotes.notes.model;

public class UpdateNoteDto {
	private String title;
	private String description;
	private boolean isPinned;
	private String color;
	private boolean isArchived;
	private boolean isTrash;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isPinned() {
		return isPinned;
	}
	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}
	public String getCo1lor() {
		return color;
	}
	public void setCo1lor(String co1lor) {
		this.color = co1lor;
	}
	public boolean isArchived() {
		return isArchived;
	}
	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}
	public boolean isTrash() {
		return isTrash;
	}
	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}
}
