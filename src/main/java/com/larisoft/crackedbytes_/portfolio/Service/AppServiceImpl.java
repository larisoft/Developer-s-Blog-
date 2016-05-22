package com.larisoft.crackedbytes_.portfolio.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.larisoft.crackedbytes_.portfolio.DAO.AppDAO;
import com.larisoft.crackedbytes_.portfolio.Model.App;

@Service
public class AppServiceImpl implements AppService{
	
	AppDAO appDao;
	
	public void setAppDao(AppDAO dao){
		this.appDao = dao;
	}

	@Override
	@Transactional
	public int addApp(App f) {
		// TODO Auto-generated method stub
		return appDao.addApp(f);
	}

	@Override
	@Transactional
	public void updateApp(App f) {
		// TODO Auto-generated method stub
		appDao.updateApp(f);
		
	}

	@Override
	@Transactional
	public List<App> listApps() {
		// TODO Auto-generated method stub
		return appDao.ListApps();
	}

	@Override
	@Transactional
	public void removeApp(int id) {
		// TODO Auto-generated method stub
		appDao.removeApp(id);
		 	
	}

	@Override
	@Transactional
	public App getAppById(int id) {
		// TODO Auto-generated method stub
		return appDao.getAppById(id);
	}

	@Override
	@Transactional
	public List<App> getAppsByAuthor(int authorId) {
		// TODO Auto-generated method stub
		return appDao.getAppsByAuthor(authorId);
	}
	
	@Override
	@Transactional
	public App getAppByUrl(String url){
		
		return appDao.getAppByUrl(url);
	}

	@Override
	@Transactional
	public List<App> getLatest(int start, int limit) {
		// TODO Auto-generated method stub
		return appDao.getLatest(start, limit);
	}

	@Override
	@Transactional
	public List<App> searchFor(String query, int start, int limit) {
		// TODO Auto-generated method stub
		return appDao.searchFor(query, start, limit);
	}

}
