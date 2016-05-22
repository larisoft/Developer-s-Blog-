package com.larisoft.crackedbytes_.User.Service;

import java.util.List;

import com.larisoft.crackedbytes_.User.Model.User;

public interface UserService { 
	
	void addUser(User user);
	void editUser(User user);
	void removeUser(User user);
	List<User> getUsers();
	User getUserById(int id);
	User getUserByEmailAndPassword(String email, String password);
	void updateUser(User user);
	User getUserByUrl(String url);
}