package com.larisoft.crackedbytes_.portfolio.Service;

import java.util.List;

import com.larisoft.crackedbytes_.portfolio.Model.App;

public interface AppService {

	int addApp(App f);
	
	void updateApp(App f);
	
	List<App> listApps();
	
	void removeApp(int id);
	
	App getAppById(int id);
	
	List<App> getAppsByAuthor(int authorId);
	
	App getAppByUrl(String url);
	
	List<App> getLatest(int start, int limit);
	
	List<App> searchFor(String query, int start, int limit);
}
