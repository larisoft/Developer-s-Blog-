package com.larisoft.crackedbytes_.blog.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

import com.larisoft.crackedbytes_.Shared.Model.Comment;
import com.larisoft.crackedbytes_.Shared.Model.Content;
import com.larisoft.crackedbytes_.Shared.Model.Prefs;
import com.larisoft.crackedbytes_.User.Service.UserService;
@Entity
@Table(name="article")
@Proxy(lazy=false)
public class Article implements Content{
	 
	@Size(min=1, max=500, message="Title must be specified")
	String title;
	
	@Size(min=1,  message="Content must be entered")
	String content;
	
	@Size(min=1, max=200, message="Please provide tags")
	String tags;
	  
	String friendly_url;
	
	long date = 0;
 
	int authorId;
	
	int views;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="id") 
	List<Comment> comments;
	 
	
	@Id
	@Column(name="id") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	public Article(){
		
		this.date = System.currentTimeMillis();
	}
	
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	
	public void setComments(List<Comment> comments){
		this.comments = comments;
	}
	
	public List<Comment> getComments(){
		
		return comments;
	}
	
	public void AddComment(Comment comment){ 
		this.comments.add(comment);
	}
	
	public void setTitle(String t){
		this.title = t;
		
		if(friendly_url==null){
			friendly_url = t.toLowerCase().replace(" ", "-");
		}
		 
	}
	
	@Override
	public String getFriendlyUrl(){ 
		return friendly_url;
	}
	
	public String getTitle(){
		return title;
	}
	
	
	public void setViews(int v){
		this.views = v;
	}
	
	
	public int getViews(){ 
		return views;
	}
	
	public void setTags(String t){
		
		this.tags  = t;
	}
	
	public String getTags(){
		return tags;
	}
	
	public void setFriendlyUrlId(String id){
		this.friendly_url = id;
	}
	 
	
	public void setDate(long date){
		this.date = date;
	}
	
	public long getDate()  { 
		
		return date;
	}
	
	public void setAuthorId(int id){ 
		this.authorId = id;
	}
	
	public int getAuthorId(){ 
		return authorId;
	}
	
	@Override
	public String getContent(){
		
		return content;
	}
	
	public void setContent(String t){
		
		this.content = t;
	}


	@Override
	public String getAge() {
		// TODO Auto-generated method stub
		long elapsed = System.currentTimeMillis() - getDate();
		return Prefs.getInstance().getAge(elapsed);
	}

 
	
	@Override
	public String getAuthorForDisplay(UserService userService) { 
		return userService.getUserById(getAuthorId()).getName();
	}


	@Override
	public int getAuthorAssetId(UserService service) {  

		return service.getUserById(authorId).getAssetId();
	}


	@Override
	public int compareTo(Content content) { 
			return new Long(getDate()).compareTo(content.getDate()); 
	}
	
	@Override
	public String toString(){
		
		return "title: "+title+ ", authorAssetId " + authorId;
	}


	@Override
	public String getAuthorUsername(UserService userService) {
		// TODO Auto-generated method stub
		return userService.getUserById(authorId).getUsername();
	}
	 
}
