package com.fundooNotes.labels.dao;

import java.util.List;

import com.fundooNotes.labels.model.Label;
import com.fundooNotes.user.model.User;

public interface LabelDao {
	public Integer save(Label label);
	public void update(Label label);
	public void delete(Label label);
	public List<Label> getLabels(User user);

}
