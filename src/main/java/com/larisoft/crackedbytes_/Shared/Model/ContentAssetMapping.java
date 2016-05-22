package com.larisoft.crackedbytes_.Shared.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="content_asset_mapping")
@Proxy(lazy=false)

//this maintains a many to many relationship, between assets(images) and the article/bug/content/user that uploaded them
public class ContentAssetMapping {
	
	//the article/blog/bug to which this asset is mapped
	int contentId;
	
	//the asset
	int assetId;
	
	//the category of the content (whether bug or article or user)
	int moduleId;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	int id;
	
	public void setContentId(int i){
		this.contentId = i;
	}
	
	public int getContentId(){
		return contentId;
	}
	
	public int getAssetId(){
		return assetId;
	}
	
	public void setAssetId(int i){
		assetId = i;
	}
	
	public int getModuleId(){
		return moduleId;
	}
	
	public void setModuleId(int id){
		this.moduleId = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int i){
		this.id = i;
	}
}
