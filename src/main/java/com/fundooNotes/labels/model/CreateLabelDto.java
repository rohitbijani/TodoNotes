package com.fundooNotes.labels.model;

import org.hibernate.validator.constraints.NotBlank;

public class CreateLabelDto {
	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
