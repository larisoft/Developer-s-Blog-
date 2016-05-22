package com.larisoft.crackedbytes_.Shared.DAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.larisoft.crackedbytes_.Shared.Model.Asset;

public class AssetDAOImpl  implements AssetDAO{
	
	SessionFactory sessionFactory;
	
	Logger logger = LoggerFactory.getLogger(AssetDAO.class);
	
	
	public void setSessionFactory(SessionFactory sess){
		this.sessionFactory  = sess;
	}

	@Override
	public int addAssert(Asset a) {
		// TODO Auto-generated method stub
		Session s = sessionFactory.getCurrentSession();
		s.persist(a);  
		return a.getId();
	}

	@Override
	public void removeAssert(Asset a) {
		// TODO Auto-generated method stub
		Session sess = sessionFactory.getCurrentSession();
		sess.delete(a);
		
	}

	@Override
	public List<Asset> listAsset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAsset(Asset a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Asset getAssetById(int id) {
		// TODO Auto-generated method stub
		Session sess = sessionFactory.getCurrentSession();
		Asset s = (Asset) sess.load(Asset.class, new Integer(id));
		return s;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Asset> listAssetsWithProjectId(int projectId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Asset.class).add(Restrictions.eq("projectId", projectId));
		List<Asset> result = (List<Asset>) criteria.list();
		logger.info("Found matching " + result.size());
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Asset> clearAssetsWithProjectId(int projectId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Asset.class).add(Restrictions.eq("projectId", projectId));
		List<Asset> result = (List<Asset>) criteria.list();
		
		for(Asset asset : result){  
			try{
			Files.delete(Paths.get(asset.getLocation()));
			logger.info("removed " + asset.getLocation());
			 
			}
			catch(IOException es){ 
				logger.info("error removing " + Paths.get(asset.getLocation()).toString() +  " because " + es.getMessage() + es.getCause());
			}
		} 
		
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Asset> getAssetByUserId(int userId) { 
		Session sess = sessionFactory.getCurrentSession();
		logger.info("looking for "+userId);
		List<Asset> result = sess.createCriteria(Asset.class).add(Restrictions.eq("userId", userId)).list();
		logger.info("found " + result.size());
		return result;
	}

	@Override
	public void removeAssetById(int id) { 
		
		Asset asset = getAssetById(id);
		removeAssert(asset);
		
	}
	
}
