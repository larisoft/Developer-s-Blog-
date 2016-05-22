package com.larisoft.crackedbytes_.blog.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.larisoft.crackedbytes_.blog.Model.Article;
import com.larisoft.crackedbytes_.portfolio.DAO.AppDAOImpl;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
	
	
	SessionFactory sessionFactory;
	 
	public void setSessionFactory(SessionFactory sess){
		this.sessionFactory = sess;
	}


	@Override
	public void addArticle(Article article) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.persist(article);
		
	}


	@Override
	public void removeArticle(Article article) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(article);
		
	}


	@Override
	public List<Article> getArticles() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Article> result = (List<Article>) session.createQuery("from Article").list();
		return result;
	}


	@Override
	public List<Article> getArticlesByAuthor(int authorId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession(); 
		Criteria criteria = session.createCriteria(Article.class).add(Restrictions.eq("authorId", authorId)); 
		List<Article> article = (List<Article>) criteria.list(); 
		return article;
	}


	@Override
	public void updateArticle(Article article) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(article);
	}


	@Override
	public Article getArticleById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Article article = (Article) session.load(Article.class, new Integer(id));
		return article;
	}


	@Override
	public Article getArticleByUrl(String url) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Article> article = session.createCriteria(Article.class).add(Restrictions.eq("friendly_url", url)).list(); 
		if(article.size()==0)return null; 
		return article.get(0);
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Article> getLatest(int start, int limit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria  criteria = session.createCriteria(Article.class);
		
		criteria.addOrder(Order.desc("date"));
		criteria.setFirstResult(start);
		criteria.setMaxResults(limit);
		List<Article> result = criteria.list();
		
		return  result;
		 
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Article> searchFor(String query_, int start, int limit) {
		Session session = sessionFactory.getCurrentSession();
		
		Criteria query = session.createCriteria(Article.class);
		query.add(Restrictions.like("title", query_, MatchMode.ANYWHERE));
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List<Article> result = query.list(); 
		return  result;
	}
	
	

}
