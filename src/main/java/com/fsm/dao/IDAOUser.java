package com.fsm.dao;

import java.util.List;

import com.fsm.models.User;


public interface IDAOUser {
	
	User insert(User user);
	void delete(String username);
	User getOneUser(String username);
	List<User> getAllUsers();
	User getUserById(int id);
	User getUserByEmail(String email);
	void update(User user);
	List<User> getAllSubscribedUsers();
}
