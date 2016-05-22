package com.larisoft.crackedbytes_.Shared.Service;

import java.util.List;

import com.larisoft.crackedbytes_.Shared.Model.ContentAssetMapping;

public interface ContentAssetMappingService {
	void addMapping(ContentAssetMapping map);
	void removeMapping(ContentAssetMapping map);
	void removeAllMapping(int module, int content );
	void updateMapping(ContentAssetMapping map); 
	List<ContentAssetMapping> getMappingsFor(int module, int content);
	ContentAssetMapping getMappingById(int id);
}
