package com.fundooNotes.labels.service;

import java.util.List;

import com.fundooNotes.labels.model.CreateLabelDto;
import com.fundooNotes.labels.model.Label;
import com.fundooNotes.labels.model.LabelDto;
import com.fundooNotes.labels.model.LabelNote;

public interface LabelService {
	public void createLabel(CreateLabelDto createLabelDto, String token);
	public void deleteLabel(Integer id, String token);
	public void updateLabel(LabelDto labelDto, String token);
	public List<Label> getLabels(String token);
	public void addLabel(LabelNote labelNote, String token);
	public void removeLabel(LabelNote labelNote, String token);
	
}
