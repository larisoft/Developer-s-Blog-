package com.larisoft.crackedbytes_.Shared.DAO;

import java.util.List;

import com.larisoft.crackedbytes_.Shared.Model.Comment;

public interface CommentDao {
	
	public void addComment(Comment comment);
	public void removeComment(Comment comment);
	public List<Comment> getCommentsFor(int contentId, int moduleId);
	
	

}
