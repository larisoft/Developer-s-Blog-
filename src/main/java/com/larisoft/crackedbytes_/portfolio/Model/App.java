package com.larisoft.crackedbytes_.portfolio.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

import com.larisoft.crackedbytes_.Shared.Model.Content;
import com.larisoft.crackedbytes_.Shared.Model.Prefs;
import com.larisoft.crackedbytes_.User.Service.UserService; 
@Entity
@Table(name="app")
@Proxy(lazy=false)
public class App implements Content {
	
	@NotNull(message="title must be entered")
	@Size(min=1, message="Title must be entered")
	String title; 
	
	@Size(min=1, message="App Url Must be provided")
	@NotNull(message ="App Url must be entered")
	String app_url;
	
	int authorId;
	
	String friendly_url;
	String source_url;
	String icon;
	  
	@NotNull(message="Description must be filled")
	@Size(min=1, message="Description must be filled")
	String description;
	
	@NotNull
	String tags;
	
	@Min(value=1, message="Please select Platform") 
	int platformId;
	
	@Min(value=1, message="Please select category")
	int categoryId;
	
	 
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	long date;
	
	
	public App(){
		this.date = System.currentTimeMillis();
	}
	
	
	public String getDateForDisplay(){
		
		try{
			
			DateFormat format = new SimpleDateFormat("MMYYddHHMMSS");
			java.util.Date d = format.parse(""+date);
			return d.toString();
		}
		catch(Exception e){
			
		}
		
		return null;
	}
	
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	 
	public void setAuthorId(int id){
		this.authorId = id;
	}
	
	public int getAuthorId(){
		return authorId;
	}
	  
	
	public void setApp_url(String app_url){
		this.app_url = app_url;
	}
	
	public String getApp_url(){
		return app_url;
	}
	
	public void setSource_url(String source_url){
		this.source_url = source_url;
	}
	
	public String getSource_url(){
		
		return source_url;
	}
		
	public String getVersion(){
		return "1.0";
	}
	
	public void setTitle(String title){ 
		this.title = title;
		this.friendly_url = title.toLowerCase().replace(" ", "-");
	}
	
	public void setFriendlyUrl(String url){ 
		this.friendly_url = url; 
	}
	
	public String getFriendlyUrl(){
		
		return friendly_url;
	}
	
	public void setCategoryId(int id){
		this.categoryId = id;
	}
	
	public int getCategoryId(){
		return categoryId;
	}
	public void setPlatformId(int id){
		this.platformId = id;
	}
	
	public int getPlatformId(){
		return platformId;
	} 
	
	public void setPlatformString(String name){
		
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setIcon(String p){
		this.icon = p;
	}
	
	public String getIcon(){
		
		return icon;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getDescriptionForDisplay(){
		return this.description.replace("\n", "<p class='paragraph'>").replace("\r", "<p class='paragraph'>");

	}
	
	public String getPath(){
		return icon;
	}
	
	public String getTags(){
		
		return tags;
	}
	
	public void setTags(String tags){
		this.tags = tags;
	}
	
	
	@Override
	public String getContent(){
		
		String content = getDescription();
		
		return content;
	}
	//validate this object when its about to be updated.
	public boolean update_validate(){
		if(title ==null || title.trim().length() < 1 || description == null || description.trim().length() <1) return false;
		
		if(app_url == null || app_url.trim().length() < 1) return false;
		
		if(platformId < 1 || categoryId < 1) return false;
		
		return true;
	}
	
	public long getDate(){
		
		return date;
	}
	
	public void setDate(long d){
		this.date = d;
	}
	
	@Override
	public String toString(){ 
		return title + ", "+description+ ", " + platformId + ", " +tags;
	}


	@Override
	public String getAge() {
		// TODO Auto-generated method stub
		long elapsed = System.currentTimeMillis() - getDate(); 
		return Prefs.getInstance().getAge(elapsed); 
	}

 
	
	@Override
	public String getAuthorForDisplay(UserService userService) { 
		return Prefs.getInstance().getAuthorName(userService, getAuthorId());
	}


	@Override
	public int getAuthorAssetId(UserService service) { 
		
		return service.getUserById(authorId).getAssetId();
	}


	@Override
	public int compareTo(Content content) {
		// TODO Auto-generated method stub
		return new Long(getDate()).compareTo(content.getDate());
	}


	@Override
	public String getAuthorUsername(UserService service) {
		// TODO Auto-generated method stub
		return service.getUserById(authorId).getUsername();
	}
}
