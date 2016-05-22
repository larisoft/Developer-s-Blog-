package com.larisoft.crackedbytes_.Shared.DAO;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.larisoft.crackedbytes_.Shared.Model.ContentAssetMapping;
import com.larisoft.crackedbytes_.Shared.Service.AssetService;

public class ContentAssetMappingDaoImpl implements ContentAssetMappingDao{

	SessionFactory session;
	
	AssetService assetService;
	
	Logger logger = LoggerFactory.getLogger(ContentAssetMappingDao.class);
	
	public void setSessionFactory(SessionFactory sess){ 
		this.session = sess;
	}
	 
	@Autowired
	@Qualifier(value="assetService")
	public void setAssetService(AssetService service){
		this.assetService = service;
	}
	
	@Override
	public void addMapping(ContentAssetMapping map) {
		// TODO Auto-generated method stub
		Session sess = session.getCurrentSession();
		sess.persist(map); 
	}

	@Override
	public void removeMapping(ContentAssetMapping map) {
		// TODO Auto-generated method stub
		Session sess = session.getCurrentSession();
		sess.delete(map);
	}

	@Override
	public void updateMapping(ContentAssetMapping map) {
		// TODO Auto-generated method stub
		Session sess = session.getCurrentSession();
		sess.update(map); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContentAssetMapping> getMappingsFor(int module, int content) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		logger.info("Searching for " + module + " and " + content);
		map.put("moduleId", module);
		map.put("contentId", content);
		
		Session sess = session.getCurrentSession();
		
		//get all mappings related to the content
		List<ContentAssetMapping> result = null;
		try{  
		result = sess.createCriteria(ContentAssetMapping.class).add(Restrictions.allEq(map)).list();
		}
		catch(Exception es){
			
		} 
		return result;
	}

	@Override
	public ContentAssetMapping getMappingById(int id) {
		// TODO Auto-generated method stub
		Session sess = session.getCurrentSession(); 
		ContentAssetMapping result = (ContentAssetMapping) sess.load(ContentAssetMapping.class, new Integer(id));
		return result;
	}

	@Override
	public void removeAllMapping(int module, int content ) {
		// TODO Auto-generated method stub
		Session sess = session.getCurrentSession();
		
		List<ContentAssetMapping> doomed = getMappingsFor(module, content);
		
		for(ContentAssetMapping map : doomed){  
			removeMapping(map); 
		}
	}
	
	
	 

}
