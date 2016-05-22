package com.larisoft.crackedbytes_.Shared.DAO;

import java.util.List;

import com.larisoft.crackedbytes_.Shared.Model.Message;

public interface MessageDao {

	public void addMessage(Message m);
	public void SetMessageRead(Message m);
	public List<Message> getMessagesForUser(int userId);  
	public int getUnreadMessagesForUser(int userId); 
}
