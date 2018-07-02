package com.fundooNotes.user.dao;

import com.fundooNotes.user.model.User;

public interface UserDao {
	
	public Integer save(User user);
	public Integer update(User user);
	public User getUserByEmail(String email);

}
