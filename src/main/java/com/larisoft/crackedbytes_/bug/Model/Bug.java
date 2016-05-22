package com.larisoft.crackedbytes_.bug.Model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

import com.larisoft.crackedbytes_.Shared.Model.Content;
import com.larisoft.crackedbytes_.Shared.Model.Prefs;
import com.larisoft.crackedbytes_.User.Service.UserService;

@Entity
@Table(name="bug")
@Proxy(lazy= false)
public class Bug implements Content {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	
	int authorId;
	int views;
	
	long date;
	
	String friendly_url;
	
	@Size(min=4)
	String title;
	
	@Size(min=4, message="Please specify Error Message")
	String error_message;
	
	
	String solution;
	
	@Size(min=1, message="Please specify technology used")
	String technologies_involved;
	
	public Bug(){
		this.date = System.currentTimeMillis();
	}
	
	
	public void setAuthorId(int id){
		this.authorId = id;
	}
	
	public int getAuthorId(){
		return authorId;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int i){
		this.id = i;
	}
	
	public void setTitle(String til){
		this.title = til;
		if(friendly_url==null){
			this.friendly_url = til.toLowerCase().replace(" ", "-");
		}
	}
	
	@Override
	public String getFriendlyUrl(){
		
		return friendly_url;
	}
	
	@Override
	public String getContent(){
		
		String content = error_message + "</br>"  + solution;
		
		return content;
		
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setError_message(String e){
		this.error_message  = e;
	}
	
	public String getError_message(){
		return error_message;
	}
	
	public String getSolution(){
		return solution;
	}
	
	public void setSolution(String s){
		
		this.solution = s;
	}
	
	public int getViews(){
		return views;
	}
	
	public void setViews(int v){
		this.views = v;
	}
	
	public void setDate(long d){
		this.date = d;
	}
	
	public long getDate(){
		return date;
	}
	
	public void setTechnologies_involved(String s){
		this.technologies_involved = s;
	}
	
	public String getTechnologies_involved(){
		return technologies_involved;
	}


	@Override
	public int compareTo(Content content) { 
			return new Long(getDate()).compareTo(content.getDate()); 
	}


	@Override
	public String getAge() {
		long elapsed = System.currentTimeMillis() - getDate();
		return Prefs.getInstance().getAge(elapsed);
	}


	@Override
	public String getAuthorForDisplay(UserService service) {
		// TODO Auto-generated method stub
		return Prefs.getInstance().getAuthorName(service, getAuthorId());
	}


	@Override
	public int getAuthorAssetId(UserService service) {
		// TODO Auto-generated method stub
		return service.getUserById(authorId).getAssetId();
	}


	@Override
	public String getAuthorUsername(UserService service) {
		// TODO Auto-generated method stub
		return service.getUserById(authorId).getUsername();
	
	}
}
