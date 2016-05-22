package com.larisoft.crackedbytes_.blog.Service;

import java.util.List;

import com.larisoft.crackedbytes_.blog.Model.Article;

public interface ArticleService {
	
	void addArticle(Article article);
	void removeArticle(Article article);
	List<Article> getArticles();
	List<Article> getArticlesByAuthor(int authorId);
	void updateArticle(Article article);
	Article getArticleById(int id);
	Article getArticleByUrl(String url);
	List<Article> getLatest(int start, int limit);
	List<Article> searchFor(String query, int start, int limit);
	
}
