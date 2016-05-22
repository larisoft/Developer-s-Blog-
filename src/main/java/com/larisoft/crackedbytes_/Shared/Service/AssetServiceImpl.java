package com.larisoft.crackedbytes_.Shared.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.larisoft.crackedbytes_.Shared.DAO.AssetDAO;
import com.larisoft.crackedbytes_.Shared.Model.Asset; 

@Service 
public class AssetServiceImpl implements AssetService {
	
	
	AssetDAO dao;
	
	
	public void setAssetDao(AssetDAO dao){
		this.dao = dao;
	}

	@Override
	@Transactional
	public int addAssert(Asset a) { 
		return dao.addAssert(a);
	}

	@Transactional
	@Override
	public void removeAssert(Asset a) {
		// TODO Auto-generated method stub
		dao.removeAssert(a);
		
	}

	@Transactional
	@Override
	public List<Asset> listAsset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void updateAsset(Asset a) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public Asset getAssetById(int id) {
		// TODO Auto-generated method stub
		return dao.getAssetById(id);
	}

	@Override
	@Transactional
	public List<Asset> getAssetByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return dao.listAssetsWithProjectId(projectId);
	}
	
	
	
	@Override
	@Transactional
	public void clearAssetWithProjectId(int projectId) {
		// TODO Auto-generated method stub
		List<Asset> results = dao.clearAssetsWithProjectId(projectId);
		
		for(Asset a : results){ 
			removeAssert(a);
		} 
	}

	@Override
	@Transactional
	public List<Asset> getAssetByUserId(int userId) {
		// TODO Auto-generated method stub
		return dao.getAssetByUserId(userId);
	}

	@Override
	public void removeAssetById(int id) {
		// TODO Auto-generated method stub
		dao.removeAssetById(id);
	}

}
