package com.larisoft.crackedbytes_.portfolio.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.larisoft.crackedbytes_.portfolio.DAO.CategoryDAO;
import com.larisoft.crackedbytes_.portfolio.Model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	CategoryDAO categoryDao;
	 
	public void setCategoryDao(CategoryDAO categoryDao){
		this.categoryDao = categoryDao;
	}
	
	@Override
	@Transactional
	public void addCategory(Category c) { 
		categoryDao.addCategory(c);
	}
	
	@Transactional
	@Override
	public void removeCategory(Category c) {
		// TODO Auto-generated method stub
		categoryDao.removeCategory(c);
	}

	@Transactional
	@Override
	public List<Category> listCategory() { 
		return categoryDao.listCategory();
	}

	@Transactional
	@Override
	public Category getCategory(int id) { 
		return categoryDao.getCategory(id);
	}

	@Transactional
	@Override
	public void updateCategory(Category category) { 
	  categoryDao.updateCategory(category);
	}

}
