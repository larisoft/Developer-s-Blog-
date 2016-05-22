package com.larisoft.crackedbytes_.Shared.Service; 
import java.util.List;

import com.larisoft.crackedbytes_.Shared.Model.Comment;

public interface CommentService { 
	public void addComment(Comment comment);
	public void removeComment(Comment comment);
	public List<Comment> getCommentsFor(int contentId, int moduleId);
}
