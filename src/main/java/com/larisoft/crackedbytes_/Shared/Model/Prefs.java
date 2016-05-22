package com.larisoft.crackedbytes_.Shared.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.larisoft.crackedbytes_.Shared.Service.ContentAssetMappingService;
import com.larisoft.crackedbytes_.Shared.Service.MessageService;
import com.larisoft.crackedbytes_.User.Service.UserService; 

//this class stores application wide settings
public class Prefs {
	 
	Logger logger = Logger.getLogger(Prefs.class);
	private static Prefs instance;
	
	 
	public String getAppTitle(){
		
		return "Ife-Opipi";
	}
	
	public static boolean isLoggedIn(HttpSession session){
		 return session.getAttribute("user")!=null;
	}
	
	private Prefs(){ 
		
		
	}
	
	public static Prefs getInstance(){ 
 		if(instance ==null) instance = new Prefs();
		return instance;
	}
	
	
	
	public String getAuthorName(UserService service, int authorId){
		
		return service.getUserById(authorId).getName();
	}
	
	
	public int getUnreadMessages(int userId, MessageService msgService){
		if(msgService==null) return 0;
		return msgService.getUnreadMessagesFor(userId);
	}
	
	
	public List<Message> getMessagesFor(int userId, MessageService msgService){
		
		if(msgService == null) return new ArrayList<Message>();
		List<Message> messages =  msgService.getMessagesForUser(userId);
		
		for(Message msg: messages){
			msg.setMessage_read(1);
			msgService.SetMessageRead(msg);
		}
		
		return messages;
	}
	
	public int getAuthorAssetId(ContentAssetMappingService service, int authorId){
		
		Module module = Module.UNKNOWN;
		List<ContentAssetMapping> results = service.getMappingsFor(module.toInt(), authorId); 
		if(results.size()>0){
			return results.get(0).getAssetId();
		}
		return 0;
	}
	
	
	public String getAge(long  elapsed){
		
		if(elapsed/(1000 * 60 * 60 * 24 * 7)> 0){
			
			long weeks = elapsed/(1000 * 60 * 60 * 24 * 7);
			
			if(weeks <2) return weeks +" Week ";
			
			return weeks + " Weeks ";
		}
		
		if(elapsed/(1000 * 60 * 60 * 24) > 0){
			
			long days = 0;
			days = elapsed/(1000 * 60 * 60 * 24);
			if(days<2) return days + " Day ";
			
			return days + " Days ";
		}
		
		if(elapsed / (1000 * 60 * 60) > 0){
			
			long hours  = 0;
			
			hours = elapsed/(60000 * 60);
			
			if(hours <2) return hours + " Hour ";
			
			return hours + " Hours ";
		}
		
		else if(elapsed/(1000 * 60) > 0){
			
			long minutes = 0; 
			minutes  = elapsed/(1000 * 60);
			if(minutes < 2) return minutes + " Minute ";
			return minutes + " Minutes ";
		}
		
		else{
			
			long seconds = elapsed/ 1000;
			
			if(seconds < 2) return seconds  + " Second ";
			
			return seconds + " Seconds ";
			
			}
			
	}
	 
	
	public String getUploadDirectory(HttpServletRequest request){
		String uploads = ""; 
		uploads  = request.getContextPath();  
		File f = new File(uploads+ File.separator + "uploads");
		if(!f.exists()) f.mkdirs();   
		logger.info("Returning " + f.toString());
		return f.toString();
	}
	  
	
	
}
