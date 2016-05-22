package com.larisoft.crackedbytes_.portfolio.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="category")
@Proxy(lazy=false)
public class Category {
	 
	@Size(min=1, max=50, message="Title must be entereed")
	String title;
	
	String description; 
	
	int authorID;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description; 
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setAuthorID(int id){
		this.authorID = id;
	}
}
