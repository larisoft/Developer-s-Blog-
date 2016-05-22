package com.larisoft.crackedbytes_.Shared.Service;

import java.util.List;

import com.larisoft.crackedbytes_.Shared.Model.Message;

public interface MessageService { 
	public void addMessage(Message message);
	public void SetMessageRead(Message m);
	public List<Message> getMessagesForUser(int userId); 
	public int getUnreadMessagesFor(int userId);
}
