package com.larisoft.crackedbytes_.User.Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

@Table(name="user")
@Proxy(lazy=false)
@Entity
public class User { 
	
	@Size(min=1, max=50, message="Email Required")
	String email;
	 
	String name;
	String surname;
	
	 
	String sex; 
	
	int ranking;
	
	long date;
	
	String phone;
	
	@Size(min=1, max=20, message="invalid password")
	String password;
	
	String confirm_password;
	
	String about;
	
	@Size(min=1, max=50, message="Invalid Username")
	String username;
	
	int validated  = 0;
	
	int assetId=0;
	 
	
	public User(){
		this.date = System.currentTimeMillis(); 
	}
	
	
	public void setAssetId(int id){
		this.assetId = id;
	}
	
	
	public int getAssetId(){
		
		return assetId;
	}
	
	public void setValidated(int i){
		this.validated = i;
	}
	
	
	public int getValidated(){
		
		return validated;
	}
	
	public void setConfirm_password(String P){
		
		this.confirm_password = P;
	}
	
	public String getConfirm_password(){
		
		return confirm_password;
	}
	 
	public void setUsername(String u){
		this.username = u;
	}
	
	public String getUsername(){
		return username;
	}
	 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	public void setPhone(String p){
		this.phone = p;
	}
	
	public void setPassword(String s){
		this.password = s;
	}
	
	public void setAbout(String about){
		this.about = about;
	}
	
	public String getAbout(){ 
		return about;
	}
	
	
	public String getPassword(){
		return password;
	}
	public String getPhone(){
		
		return phone;
	}
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		
		return email;
	}
	
	
	public void setSurname(String s){
		this.surname = s;
	}
	
	public String getSurname(){
		return surname;
	}
	
	public String getName(){
		
		return name;
	}
	
	public void setName(String n){
		name = n;
	}
	 
	public void setSex(String c){ 
		this.sex = c;
	}
	
	public String getSexForDisplay(){
		
		if(sex.equals("m"))return "Male"; 
		return "Female"; 
	}
	
	public String getSex(){
		return sex;
	}
	
	
	public long getDate(){
 		
		return date;
		  
	}
}



