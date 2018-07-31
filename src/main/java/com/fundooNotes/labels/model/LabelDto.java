package com.fundooNotes.labels.model;

import org.hibernate.validator.constraints.NotBlank;

public class LabelDto {
	private int id;
	@NotBlank
	private String name;
	
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
}
