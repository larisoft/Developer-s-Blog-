package com.larisoft.crackedbytes_.portfolio.Service;

import java.util.List;

import javax.transaction.Transactional;
 
import org.springframework.stereotype.Service;

import com.larisoft.crackedbytes_.portfolio.DAO.PlatformDAO;
import com.larisoft.crackedbytes_.portfolio.Model.Platform;

@Service
public class PlatformServiceImpl implements PlatformService{
	  
	PlatformDAO platformDao;
	 
	 
	public void setPlatformDao(PlatformDAO dao){
		this.platformDao = dao;
		
	}
	
	@Override
	@Transactional
	public void insert(Platform p) { 
		platformDao.insert(p);
		
	}

	@Override
	@Transactional
	public void remove(Platform p) { 
		platformDao.remove(p);
	}

	@Override
	@Transactional
	public List<Platform> listPlatforms() { 
		return platformDao.listPlatforms(); 
	}

	@Override
	@Transactional
	public void update(Platform p) { 
		 platformDao.update(p);
	}
	
	@Override
	@Transactional
	public Platform getPlatformById(int id){
		
		return platformDao.getPlatformById(id);
	}

}
