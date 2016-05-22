package com.larisoft.crackedbytes_.portfolio.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.larisoft.crackedbytes_.portfolio.Model.Platform;

@Repository
public class PlatformDAOImpl implements PlatformDAO {
	
	SessionFactory sessionFactory; 
	public void setSessionFactory(SessionFactory session){ 
		this.sessionFactory = session;
	}
	
	@Override
	public void insert(Platform p) {
		Session se = sessionFactory.getCurrentSession();
		se.persist(p); 
	}

	@Override
	public void remove(Platform p) {
		Session se = sessionFactory.getCurrentSession();
		se.delete(p); 
		
	}

	@Override 
	@SuppressWarnings("unchecked")
	public List<Platform> listPlatforms() {
		// TODO Auto-generated method stub
		Session se = sessionFactory.getCurrentSession();
		List<Platform> platforms = (List<Platform>)  se.createQuery("from Platform").list();
		return platforms;
	}

	@Override
	public void update(Platform p) {
		// TODO Auto-generated method stub
		Session se = sessionFactory.getCurrentSession();
		se.update(p);
	}
	
	
	@Override
	public Platform getPlatformById(int id){ 
	Session se = sessionFactory.getCurrentSession();
	Platform platform = (Platform) se.load(Platform.class, new Integer(id));
	return platform;
	}

}
