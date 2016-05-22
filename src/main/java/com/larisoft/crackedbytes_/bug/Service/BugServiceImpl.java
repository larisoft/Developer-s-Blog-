package com.larisoft.crackedbytes_.bug.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.larisoft.crackedbytes_.bug.Dao.BugDAO;
import com.larisoft.crackedbytes_.bug.Model.Bug;

@Service
@Transactional
public class BugServiceImpl implements BugService{

	BugDAO bugDao;
	
	public void setBugDao(BugDAO dao){
		this.bugDao = dao;
	}
	
	@Override
	public void addBug(Bug b) {
		// TODO Auto-generated method stub
		bugDao.addBug(b);
	}

	@Override
	public void removeBug(Bug b) {
		// TODO Auto-generated method stub
		bugDao.removeBug(b);
	}

	@Override
	public List<Bug> listBugs(int authorId) {
		// TODO Auto-generated method stub
		return	bugDao.listBugs(authorId);
	}

	@Override
	public void updateBug(Bug b) {
		// TODO Auto-generated method stub
		bugDao.updateBug(b); 
	}

	@Override
	public Bug getBugById(int id) {
		// TODO Auto-generated method stub
		return bugDao.getBugById(id);
	}

	@Override
	public Bug getBugByUrl(String url) {
		// TODO Auto-generated method stub
		return bugDao.getBugByUrl(url);
	}

	@Override
	public List<Bug> getLatest(int start, int limit) {
		// TODO Auto-generated method stub
		return bugDao.getLatest(start, limit);
	}

	@Override
	public List<Bug> searchFor(String query, int start, int limit) {
		// TODO Auto-generated method stub
		return bugDao.searchFor(query, start, limit);
	}

}
