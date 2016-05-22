package com.larisoft.crackedbytes_.portfolio.DAO;

import java.util.List;

import com.larisoft.crackedbytes_.portfolio.Model.App;

public interface AppDAO {

	int addApp(App f);
	void removeApp(int id);
	List<App> ListApps();
	void updateApp(App a);
	App getAppById(int id);
	List<App> getAppsByAuthor(int authorId);
	App getAppByUrl(String url);
	List<App> getLatest(int start, int limit);
	List<App> searchFor(String query, int start, int limit);
}
