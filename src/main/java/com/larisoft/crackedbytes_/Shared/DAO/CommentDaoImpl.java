package com.larisoft.crackedbytes_.Shared.DAO;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.larisoft.crackedbytes_.Shared.Model.Comment;

public class CommentDaoImpl implements CommentDao {

	SessionFactory sessionFactory; 
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	
	
	
	
	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.persist(comment);
	}

	@Override
	public void removeComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Comment> getCommentsFor(int contentId, int moduleId) {
		

		Session session = sessionFactory.getCurrentSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("content_id", contentId);
		map.put("module_id", moduleId);
		Criteria criteria = session.createCriteria(Comment.class);
		criteria.add(Restrictions.allEq(map));
		
		List<Comment> result = criteria.list();
		return result;
		
	}

}
