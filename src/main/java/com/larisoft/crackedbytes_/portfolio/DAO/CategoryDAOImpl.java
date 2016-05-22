package com.larisoft.crackedbytes_.portfolio.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.larisoft.crackedbytes_.portfolio.Model.Category;

@Repository
public class CategoryDAOImpl  implements CategoryDAO{
	
	SessionFactory sessionFactory;
	Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addCategory(Category c) { 
		Session sess = sessionFactory.getCurrentSession();
		sess.persist(c); 
	}

	@Override
	public void removeCategory(Category c) {
		// TODO Auto-generated method stub 
		Session sess  = sessionFactory.getCurrentSession();
		sess.delete(c);
		
	}
 
	@Override 
	@SuppressWarnings("unchecked")
	public List<Category> listCategory() {
		// TODO Auto-generated method stub
		Session sess = sessionFactory.getCurrentSession();
		List<Category> result = (List<Category>) sess.createQuery("from Category").list();
		return result;
	}

	@Override
	public void updateCategory(Category c) {
		// TODO Auto-generated method stub
		Session sess = sessionFactory.getCurrentSession();
		sess.update(c);
		
	}

	@Override
	public Category getCategory(int id) { 
		Session sess = sessionFactory.getCurrentSession();
		Category category = (Category) sess.load(Category.class, new Integer(id));
		return category;
	}

}
