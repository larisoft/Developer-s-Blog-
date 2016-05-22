package com.larisoft.crackedbytes_.Shared.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.larisoft.crackedbytes_.Shared.DAO.ContentAssetMappingDao;
import com.larisoft.crackedbytes_.Shared.Model.Asset;
import com.larisoft.crackedbytes_.Shared.Model.ContentAssetMapping;

public class ContentAssetMappingServiceImpl implements ContentAssetMappingService{

	ContentAssetMappingDao mapDao;
	
	public void setContentAssetMappingDao(ContentAssetMappingDao dao){
		this.mapDao = dao;
	}
	
	@Override
	@Transactional
	public void addMapping(ContentAssetMapping map) {
		// TODO Auto-generated method stub
		mapDao.addMapping(map);
	}
	
	@Transactional
	@Override
	public void removeMapping(ContentAssetMapping map) {
		// TODO Auto-generated method stub
		mapDao.removeMapping(map);
		
	}

	@Transactional
	@Override
	public void updateMapping(ContentAssetMapping map) {
		// TODO Auto-generated method stub
		mapDao.updateMapping(map);
	}

	@Transactional
	@Override
	public List<ContentAssetMapping> getMappingsFor(int module, int content) {
		// TODO Auto-generated method stub
		return mapDao.getMappingsFor(module, content);
	}

	@Override
	@Transactional
	public ContentAssetMapping getMappingById(int id) {
		// TODO Auto-generated method stub
		return mapDao.getMappingById(id);
	}

	@Override
	@Transactional
	public void removeAllMapping(int module, int content ) {
		// TODO Auto-generated method stub
		mapDao.removeAllMapping(module, content );
	}

}
