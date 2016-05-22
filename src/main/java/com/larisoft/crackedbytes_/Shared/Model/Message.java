package com.larisoft.crackedbytes_.Shared.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message {
	
	
	String message;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	int id;
	
	int message_read;
	String email;
	int receiver_id;
	long date;
	
	
	public Message(){ 
		this.date = System.currentTimeMillis();
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String mess){
		this.message = mess;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int i){
		this.id = i;
	}
	
	
	public boolean isRead(){ 
		return message_read==1;
	}
	
	public void setMessage_read(int i){
		this.message_read = i;
	}
	
	public String getAge(){ 
	 long elapsed =  System.currentTimeMillis() - date;
	 return Prefs.getInstance().getAge(elapsed);
	}
	
	public long getDate(){ 
		return date;
	}
	
	public void setDate(long da){
		this.date = da;
	}
	
	public void setEmail(String e){
		this.email = e;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setReceiver_id(int id){
		this.receiver_id = id;
	}
	
	public int getReceiver_id(){
		return receiver_id;
	}
}
