package com.larisoft.crackedbytes_.portfolio.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="platform")
@Proxy(lazy=false)
public class Platform {
	
	@NotNull(message= "Title must be provided")
	@Size(min=1, max=50, message="Title must be provided")
	String title;
	String image;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	int id;
	
	
	public void setTitle(String tit){
		this.title = tit;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setImage(String image){
		this.image = image;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	

	
}
