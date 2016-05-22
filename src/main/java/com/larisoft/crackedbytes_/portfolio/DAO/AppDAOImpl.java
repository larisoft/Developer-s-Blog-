package com.larisoft.crackedbytes_.portfolio.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.larisoft.crackedbytes_.blog.Model.Article;
import com.larisoft.crackedbytes_.portfolio.Model.App;

@Repository
public class AppDAOImpl implements AppDAO {
	
	org.slf4j.Logger logger =  LoggerFactory.getLogger(AppDAOImpl.class);
	SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	} 

	@Override
	public int addApp(App f) {  
		Session sess = sessionFactory.getCurrentSession();
		sess.persist(f);
		logger.info("Saved " + f);
		return f.getId();
	}

	@Override
	public void removeApp(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<App> ListApps() {
		// TODO Auto-generated method stub
		Session sess = sessionFactory.getCurrentSession();
		List<App> result = sess.createQuery("from File").list();
		return result;
	}

	@Override
	public void updateApp(App f) {
		// TODO Auto-generated method stub
		Session sess = sessionFactory.getCurrentSession();
		sess.update(f);
		logger.info("updaed " + f);
		
	}

	@Override
	public App getAppById(int id) { 
		Session sess = sessionFactory.getCurrentSession();
		App file = (App) sess.load(App.class, new Integer(id));
		return file;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<App> getAppsByAuthor(int authorId) {
		// TODO Auto-generated method stub 
		Session sess = sessionFactory.getCurrentSession(); 
		Criteria criteria = sess.createCriteria(App.class).add(Restrictions.eq("authorId", authorId)); 
		List<App> result = (List<App>) criteria.list();
		return result;
	}

	
	@Override
	public App getAppByUrl(String url){
		Session session = sessionFactory.getCurrentSession();
		List<App> app = session.createCriteria(App.class).add(Restrictions.eq("friendly_url", url)).list();
		
		if(app.size()==0)return null;  
		return app.get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<App> getLatest(int start, int limit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession(); 
		Criteria criteria = session.createCriteria(App.class);
		criteria.addOrder(Order.desc("date"));
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		
		List<App> result = criteria.list();
		return result;
	}

	@Override
	public List<App> searchFor(String query, int start, int limit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession(); 
		Criteria criteria = session.createCriteria(App.class);
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		criteria.add(Restrictions.like("title", query, MatchMode.ANYWHERE));
		
		return criteria.list();
	}
}
