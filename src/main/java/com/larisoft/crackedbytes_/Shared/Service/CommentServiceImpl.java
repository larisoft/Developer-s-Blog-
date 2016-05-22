package com.larisoft.crackedbytes_.Shared.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.larisoft.crackedbytes_.Shared.DAO.CommentDao;
import com.larisoft.crackedbytes_.Shared.Model.Comment;

@Transactional
public class CommentServiceImpl implements CommentService {
	
	
	CommentDao commentDao;
	
	
	public void setCommentDao(CommentDao commentDao){
		this.commentDao = commentDao;
	}

	@Override
	public void addComment(Comment comment) {
		// TODO Auto-generated method stub  
		commentDao.addComment(comment);
	}

	@Override
	public void removeComment(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Comment> getCommentsFor(int contentId, int moduleId) {
		// TODO Auto-generated method stub
		return commentDao.getCommentsFor(contentId, moduleId);
	}

}
