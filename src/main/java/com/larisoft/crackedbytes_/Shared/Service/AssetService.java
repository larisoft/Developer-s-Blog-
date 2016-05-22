package com.larisoft.crackedbytes_.Shared.Service;

import java.util.List;

import com.larisoft.crackedbytes_.Shared.Model.Asset;

public interface AssetService {
	
	int addAssert(Asset a);
	void removeAssert(Asset a);
	List<Asset> listAsset();
	void updateAsset(Asset a);
	Asset getAssetById(int id); 
	List<Asset> getAssetByProjectId(int projectId);
	void clearAssetWithProjectId(int projectId);
	List<Asset> getAssetByUserId(int userId);
	void removeAssetById(int id);
}
