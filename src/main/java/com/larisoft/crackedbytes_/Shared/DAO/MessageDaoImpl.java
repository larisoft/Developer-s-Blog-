package com.larisoft.crackedbytes_.Shared.DAO;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.larisoft.crackedbytes_.Shared.Model.Message;

public class MessageDaoImpl implements MessageDao{

	SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addMessage(Message m) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.persist(m);
		
	}

	@Override
	public void SetMessageRead(Message m) {
		// TODO Auto-generated method stub
		m.setMessage_read(1);
		Session session = sessionFactory.getCurrentSession();
		session.update(m);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public int getUnreadMessagesForUser(int userId) { 
		Session session = sessionFactory.getCurrentSession();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("message_read", 0);
		map.put("receiver_id", userId);
		List<Message> messages = session.createCriteria(Message.class).add(Restrictions.allEq(map)).list(); 
		return messages.size();
		 
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Message> getMessagesForUser(int userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Message> messages = session.createCriteria(Message.class).add(Restrictions.eq("receiver_id", userId)).list(); 
		return messages;
	}

}
