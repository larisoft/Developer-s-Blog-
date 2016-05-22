package com.larisoft.crackedbytes_.blog.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.larisoft.crackedbytes_.blog.DAO.ArticleDAO;
import com.larisoft.crackedbytes_.blog.Model.Article;

@Service
public class ArticleServiceImpl implements ArticleService {
	
	ArticleDAO articleDao;
	
	public void setArticleDao(ArticleDAO dao){
		this.articleDao = dao;
	}

	@Override
	@Transactional
	public void addArticle(Article article) {
		// TODO Auto-generated method stub
		articleDao.addArticle(article);
	}

	@Override
	@Transactional
	public void removeArticle(Article article) {
		// TODO Auto-generated method stub
		articleDao.removeArticle(article);
	}

	@Override
	@Transactional
	public List<Article> getArticles() {
		// TODO Auto-generated method stub
		return articleDao.getArticles();
	}

	@Override
	@Transactional
	public List<Article> getArticlesByAuthor(int authorId) {
		// TODO Auto-generated method stub
		return articleDao.getArticlesByAuthor(authorId);
	}

	@Override
	@Transactional
	public void updateArticle(Article article) {
		// TODO Auto-generated method stub
		articleDao.updateArticle(article);
	}

	@Override
	@Transactional
	public Article getArticleById(int id) {
		// TODO Auto-generated method stub
		return articleDao.getArticleById(id);
	}

	@Override
	@Transactional
	public Article getArticleByUrl(String url) {
		// TODO Auto-generated method stub
		return articleDao.getArticleByUrl(url);
	}

	@Override
	@Transactional
	public List<Article> getLatest(int start, int limit) {
		// TODO Auto-generated method stub
		return articleDao.getLatest(start, limit);
	}

	@Override
	@Transactional
	public List<Article> searchFor(String query, int start, int limit) {
		// TODO Auto-generated method stub
		return articleDao.searchFor(query, start, limit);
	}

}
