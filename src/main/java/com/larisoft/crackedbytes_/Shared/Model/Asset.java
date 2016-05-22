package com.larisoft.crackedbytes_.Shared.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="asset")
@Proxy(lazy=false)
public class Asset {
	 
	String name;
	int userId;
	String location;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	int id;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	
	public void setName(String name){
		this.name= name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setUserId(int id){
		this.userId = id;
	}
	
	public int getUserId(){
		return userId;
	}
	
	
	public  void setLocation(String location){
		
		this.location = location;
	}
	
	public String getLocation(){
		return location;
	}

}
