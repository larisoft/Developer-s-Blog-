package com.larisoft.crackedbytes_.portfolio.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Validate_file_upload {
	 
	int max_image_size = (1024 * 1024);
	int min_image_size = 1024 * 10;
	MultipartFile[]   images;
	String file_url, source_url;
	int id;
	
	List<String> errors = new ArrayList<String>();
	public Validate_file_upload(MultipartFile[] app_image, String id){
		 
		this.images = app_image;  
		try{
		int i = Integer.parseInt(id);
		this.id = i;
		}
		catch(Exception es){
			
		}
	}
	
	public boolean validate(){ 
		boolean result = true; 
		
		for(int i = 0; i < images.length; i++){
			
			try{
			if(images[i] == null || images[i].getBytes().length < 10 || images[i].getBytes().length > max_image_size){
				errors.add("Screenshot "+(i+1) + ": Images must be less than 1mb and greater than 10kb in size");
				result = false;
			}
			}
			catch(IOException es){
				errors.add("Some images are currupt");
			}
		}
		if(id==0){
			errors.add("Fatal Error! App is not in database!");
			result = false;
		}
		
		return result;
	}
	
	public List<String> getErrors(){
		
		return errors;
	}
}
