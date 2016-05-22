package com.larisoft.crackedbytes_.portfolio.Service;

import java.util.List;

import com.larisoft.crackedbytes_.portfolio.Model.Platform;

public interface PlatformService {
	
	void insert(Platform p);
	void remove(Platform p);
	List<Platform> listPlatforms();
	void update(Platform p);
	Platform getPlatformById(int id);

}
