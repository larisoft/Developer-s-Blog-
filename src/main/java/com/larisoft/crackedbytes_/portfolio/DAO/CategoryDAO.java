package com.larisoft.crackedbytes_.portfolio.DAO;

import java.util.List;

import com.larisoft.crackedbytes_.portfolio.Model.Category;

public interface CategoryDAO { 
	
	void addCategory(Category c);
	void removeCategory(Category c);
	List<Category> listCategory();
	void updateCategory(Category c);
	Category getCategory(int id);
}
