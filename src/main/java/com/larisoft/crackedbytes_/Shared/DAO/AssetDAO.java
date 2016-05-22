package com.larisoft.crackedbytes_.Shared.DAO;

import java.util.List;

import com.larisoft.crackedbytes_.Shared.Model.Asset;

public interface AssetDAO {  
	int addAssert(Asset a);
	void removeAssert(Asset a);
	List<Asset> listAsset();
	void updateAsset(Asset a);
	Asset getAssetById(int id);
	List<Asset> listAssetsWithProjectId(int projectId);
	List<Asset> clearAssetsWithProjectId(int projectId);
	List<Asset> getAssetByUserId(int userId);
	void removeAssetById(int id);
}

