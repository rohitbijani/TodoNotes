package com.fundooNotes.labels.service;

import java.util.List;

import com.fundooNotes.labels.model.CreateLabelDto;
import com.fundooNotes.labels.model.Label;
import com.fundooNotes.labels.model.LabelDto;

public interface LabelService {
	public void createLabel(CreateLabelDto createLabelDto, String token);
	public void deleteLabel(LabelDto labelDto, String token);
	public void updateLabel(LabelDto labelDto, String token);
	public List<Label> getLabels(String token);
	
}
