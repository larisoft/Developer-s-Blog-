package com.larisoft.crackedbytes_;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.larisoft.crackedbytes_.Shared.DAO.ContentAssetMappingDao;
import com.larisoft.crackedbytes_.Shared.Model.ContentAssetMapping;
import com.larisoft.crackedbytes_.Shared.Model.Module;
import com.larisoft.crackedbytes_.Shared.Model.Prefs;
import com.larisoft.crackedbytes_.Shared.Service.ContentAssetMappingService;
import com.larisoft.crackedbytes_.User.Model.User;
import com.larisoft.crackedbytes_.User.Service.UserService;
import com.larisoft.crackedbytes_.User.UserDAO.UserDao;


@Controller
@RequestMapping("/user")
public class UserController { 
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	UserService userService; 
	HttpSession session;
	
	
	ContentAssetMappingService contentAssetMappingService;


	
	@Autowired
	@Qualifier("contentAssetMappingService")
	public void setContentAssetMapping(ContentAssetMappingService dao){
		this.contentAssetMappingService = dao;
	}
	
		
	@Autowired
	public void setHttpSession(HttpSession session){
		this.session = session;
	}
	 
	@Autowired
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	@RequestMapping("view_user/{id}")
	public String view_user(@PathVariable int id, Model model){

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login";  
		
		return "redirect:/user/edit_user/"+id; 
	}
	
	
	@RequestMapping("edit_user/{id}")
	public String edit_user(@PathVariable int id, Model model){  
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login";  
		User user = userService.getUserById(id);
		Module module = Module.UNKNOWN;
		module.setController("user");
		int moduleId = module.toInt();
		logger.info("module Id " + moduleId);
		
	    List<ContentAssetMapping> assets = contentAssetMappingService.getMappingsFor(moduleId, id);
		 
		String this_url = "user/edit_user/"+id;
		model.addAttribute("this_url", this_url.replace("/", "-"));
		model.addAttribute("assets", assets);
		model.addAttribute("sexes", getSexOptions());
		model.addAttribute("user", user); 
		model.addAttribute("section", "user");
		model.addAttribute("sub_section", "edit_user");
		model.addAttribute("prefs", Prefs.getInstance());
		return "/backend/user/edit_user";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add_user(@Valid User user, BindingResult result){ 
		if(result.hasErrors()){ 
			return "/backend/user/edit_user/";
		}
		
		if(user.getId()==0){
			userService.addUser(user);
		}
		else{ 
			userService.updateUser(user);
		}
		
		return "redirect:/user/view_user/"+user.getId();
	}
	
	
	private HashMap<String, String> getSexOptions(){
		
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("", "");
		result.put("m", "Male");
		result.put("f", "Female");
		result.put("o", "Other"); 
		return result;
	}
	
}
