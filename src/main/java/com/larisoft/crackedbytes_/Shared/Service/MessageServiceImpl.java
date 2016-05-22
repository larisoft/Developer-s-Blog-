package com.larisoft.crackedbytes_.Shared.Service;

import java.util.List;

import javax.transaction.Transactional;

import com.larisoft.crackedbytes_.Shared.DAO.MessageDao;
import com.larisoft.crackedbytes_.Shared.Model.Message;


@Transactional
public class MessageServiceImpl implements MessageService {
	
	MessageDao messageDao;
	
	public void setMessageDao(MessageDao messageD){
		this.messageDao = messageD;
	}

	@Override
	public void addMessage(Message m) {
		// TODO Auto-generated method stub
		messageDao.addMessage(m);
	}

	@Override
	public void SetMessageRead(Message m) {
		// TODO Auto-generated method stub
		messageDao.SetMessageRead(m);
	}

	@Override
	public List<Message> getMessagesForUser(int userId) {
		// TODO Auto-generated method stub
		return messageDao.getMessagesForUser(userId);
	}

	@Override
	public int getUnreadMessagesFor(int userId) {
		// TODO Auto-generated method stub
		return messageDao.getUnreadMessagesForUser(userId);
	}
	
	

}
