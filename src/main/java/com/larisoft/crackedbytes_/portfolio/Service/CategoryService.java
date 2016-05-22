package com.larisoft.crackedbytes_.portfolio.Service;

import java.util.List;

import com.larisoft.crackedbytes_.portfolio.Model.Category;

public interface CategoryService {

	void addCategory(Category c);
	void removeCategory(Category c);
	List<Category> listCategory();
	Category getCategory(int id);
	void updateCategory(Category category);
	
}
