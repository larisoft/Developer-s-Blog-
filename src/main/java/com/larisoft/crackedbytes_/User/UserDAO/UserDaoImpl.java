package com.larisoft.crackedbytes_.User.UserDAO;

import java.util.HashMap;
import java.util.List; 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.larisoft.crackedbytes_.User.Model.User; 
 

@Repository
public class UserDaoImpl implements UserDao { 
	
	SessionFactory sessionFactory;
	 
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}


	@Override
	public void addUser(User user) {
		Session sess  = sessionFactory.getCurrentSession();
		sess.persist(user);
		
	}


	@Override
	public void editUser(User user) {
		// TODO Auto-generated method stub
		Session sess  = sessionFactory.getCurrentSession();
		sess.update(user); 
	}


	@Override
	public void removeUser(User user) {
		// TODO Auto-generated method stub
		Session sess  = sessionFactory.getCurrentSession();
		sess.update(user);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		Session sess  = sessionFactory.getCurrentSession();
		List<User> result  = (List<User>) sess.createQuery("from User").list();
		return result;
	}


	@Override
	public User getUserById(int id) {
		Session sess  = sessionFactory.getCurrentSession();
		User user = (User) sess.load(User.class, new Integer(id));
		return user;
	}


	@Override
	@SuppressWarnings("unchecked")
	public User getUserByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		Session sess = sessionFactory.getCurrentSession();
		HashMap<String, String> map  = new HashMap<String, String>();
		map.put("password", password);
		map.put("email", email);
		List<User>  user = sess.createCriteria(User.class).add(Restrictions.allEq(map)).list(); 
		if(user.isEmpty()) return null; 
		return user.get(0);
	}


	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		Session sess = sessionFactory.getCurrentSession();
		sess.update(user);
	}


	@Override
	public User getUserByUrl(String url) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<User> users = session.createCriteria(User.class).add(Restrictions.eq("username", url)).list();
		
		if(users.size()==0)return null;  
		return users.get(0);
	}

 
	
}
