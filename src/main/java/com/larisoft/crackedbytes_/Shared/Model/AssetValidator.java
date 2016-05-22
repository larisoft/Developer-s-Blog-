package com.larisoft.crackedbytes_.Shared.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class AssetValidator implements Validator {

	boolean result = true; 
	int max_size = (1024 * 1024);
	int min__size = 1024 * 10;
	List<String> message  = new ArrayList<String>();
	public AssetValidator(MultipartFile file){
		
		if(file==null){
			result = false;
		}
		
		try{
		if(file.getBytes().length < 10){
			message.add("Invalid File");
		}
		
		if(file.getBytes().length > max_size){
			message.add("Invalid File");
		}
		}
		catch(Exception es){
			result = false;
			message.add("Error Processing File");
		}
		
	}
	
	
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return result;
	}


	@Override
	public List<String> getErrors() {
		// TODO Auto-generated method stub
		return message;
	}
	
	
	

}
