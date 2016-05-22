package com.larisoft.crackedbytes_.bug.Dao;

import java.util.List;

import com.larisoft.crackedbytes_.bug.Model.Bug;

public interface BugDAO {

		void addBug(Bug b);
		void removeBug(Bug b);
		List<Bug> listBugs(int authorId);
		void updateBug(Bug b);
		Bug getBugById(int id);
		Bug getBugByUrl(String url);
		List<Bug> getLatest(int start, int limit);
		List<Bug> searchFor(String query, int start, int limit);
}
