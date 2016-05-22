package com.larisoft.crackedbytes_.bug.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.larisoft.crackedbytes_.blog.Model.Article;
import com.larisoft.crackedbytes_.bug.Model.Bug;

public class BugDaoImpl implements BugDAO{

	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public void addBug(Bug b) { 
		Session sess = sessionFactory.getCurrentSession();
		sess.persist(b); 
	}

	@Override
	public void removeBug(Bug b) { 
		Session sess = sessionFactory.getCurrentSession();
		sess.delete(sess);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bug> listBugs(int authorId) { 
		Session sess = sessionFactory.getCurrentSession();
		Criteria criteria = sess.createCriteria(Bug.class).add(Restrictions.eq("authorId", authorId)); 
		List<Bug> result = criteria.list();
		return result;
	}

	@Override
	public void updateBug(Bug b) { 
		Session sess = sessionFactory.getCurrentSession();
		sess.update(b);
	}

	@Override
	public Bug getBugById(int id) { 
		Session sess = sessionFactory.getCurrentSession();
		Bug b = (Bug) sess.load(Bug.class, new Integer(id));
		return b;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Bug getBugByUrl(String url){
		Session session = sessionFactory.getCurrentSession();
		List<Bug> bug = session.createCriteria(Bug.class).add(Restrictions.eq("friendly_url", url)).list(); 
		if(bug.size()==0)return null;  
		return bug.get(0);
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Bug> getLatest(int start, int limit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession(); 
		Criteria criteria = session.createCriteria(Bug.class);
		criteria.addOrder(Order.desc("date"));
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		
		List<Bug> result = criteria.list();
		return result;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Bug> searchFor(String query, int start, int limit) {
		// TODO Auto-generated method stub
		Session sess = sessionFactory.getCurrentSession();
		Criteria criteria = sess.createCriteria(Article.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.add(Restrictions.like("title", query, MatchMode.ANYWHERE));
		List<Bug> result = criteria.list();
		
		return result;
	}

}
