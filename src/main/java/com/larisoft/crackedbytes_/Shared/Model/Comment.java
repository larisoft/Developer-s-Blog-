package com.larisoft.crackedbytes_.Shared.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty; 

@Entity
@Table(name="comment")
public class Comment {
	 
	int content_id;
	int module_id;
	
	//the url on which this comment is displayed
	String url;
	
	@NotEmpty(message="Comment must be specfied")
	String comment;
	
	@NotEmpty(message="Email must be specified")
	String email;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
	
	long date; 
	
	
	public Comment(){ 
		this.date = System.currentTimeMillis();
	}
	
	public String getComment(){
		return comment;
	}
	
	public void setComment(String c){
		this.comment  = c;
	}
	 
	public int getId(){
		return id;
	}
	public void setId(int i ){
		this.id = i;
	}
	
	public void setEmail(String s){
		this.email = s;
	}
	public String getEmail(){
		return email;
	}
	
	public void setContent_id(int id){
		this.content_id = id;
	}
	
	public int getContent_id(){
		return content_id;
	}
	
	public int getModule_id(){
		return module_id;
	}
	
	public void setModule_id(int id){
		this.module_id  = id;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public String getAge(){
		
		long elapsed = System.currentTimeMillis() - date;
		return Prefs.getInstance().getAge(elapsed);
	}
	
	public void setDate(long date){
		this.date = date;
	}
	
	public long getDate(){
		return this.date;
	}

}
