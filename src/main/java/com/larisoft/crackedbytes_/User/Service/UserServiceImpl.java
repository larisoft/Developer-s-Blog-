package com.larisoft.crackedbytes_.User.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.larisoft.crackedbytes_.User.Model.User;
import com.larisoft.crackedbytes_.User.UserDAO.UserDao;

@Service
public class UserServiceImpl implements UserService {

	UserDao userDao; 
	public void setUserDao(UserDao dao){	
		this.userDao = dao;
	}
	
	@Override
	@Transactional
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	@Override
	@Transactional
	public void editUser(User user) {
		// TODO Auto-generated method stub
		userDao.editUser(user);
	}

	@Override
	@Transactional
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		userDao.removeUser(user);
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userDao.getUsers();
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	@Override
	@Transactional
	public User getUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userDao.getUserByEmailAndPassword(email, password);
	}

	
	@Override
	@Transactional
	public void updateUser(User user){
		userDao.updateUser(user);
	}

	@Override
	@Transactional
	public User getUserByUrl(String url) {
		// TODO Auto-generated method stub
		return userDao.getUserByUrl(url);
	}
}
