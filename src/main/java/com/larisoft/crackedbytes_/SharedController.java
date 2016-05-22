package com.larisoft.crackedbytes_;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.larisoft.crackedbytes_.Shared.Model.Asset;
import com.larisoft.crackedbytes_.Shared.Model.AssetValidator;
import com.larisoft.crackedbytes_.Shared.Model.Comment;
import com.larisoft.crackedbytes_.Shared.Model.ContentAssetMapping;
import com.larisoft.crackedbytes_.Shared.Model.Module;
import com.larisoft.crackedbytes_.Shared.Model.Prefs;
import com.larisoft.crackedbytes_.Shared.Model.Validator;
import com.larisoft.crackedbytes_.Shared.Service.AssetService;
import com.larisoft.crackedbytes_.Shared.Service.CommentService;
import com.larisoft.crackedbytes_.Shared.Service.ContentAssetMappingService;
import com.larisoft.crackedbytes_.Shared.Service.MessageService;
import com.larisoft.crackedbytes_.User.Model.User;
import com.larisoft.crackedbytes_.User.Service.UserService;

@Controller
@RequestMapping(value="/shared")
public class SharedController {
	
	Logger logger = LoggerFactory.getLogger(SharedController.class); 
	
	AssetService assetService;
	UserService userService; 
	
	ContentAssetMappingService contentAssetMappingService; 
	HttpSession session;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	MessageService messageService;
	
	
	@Autowired
	public void setSession(HttpSession session){
		this.session = session;
	}
	
	@Autowired
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	@Autowired
	@Qualifier(value="contentAssetMappingService")
	public void setContentAssetMappingService(ContentAssetMappingService service){
		this.contentAssetMappingService =  service;
	}
	
	
	@Autowired
	@Qualifier(value="assetService")
	public void setAssertService(AssetService service){
		this.assetService = service;
	}
	 
	
	@RequestMapping(value="comment/add")
	public String addComment(@Valid Comment comment, BindingResult result, Model model){
		
		if(result.hasErrors()){
			logger.info("error with comment");
			for(ObjectError error : result.getAllErrors()){
				logger.info(error.getDefaultMessage());
			}
			return "redirect:"+comment.getUrl(); 
		}
		logger.info("comment inserted");
		commentService.addComment(comment);
		return "redirect:"+comment.getUrl();
		
		
	}
	
	
	@RequestMapping(value="remove_mapping")
	public String remove_mapping(@RequestParam("request_url") String request_url, @RequestParam("doomed_assets") String doomed_assets){
	 
		String[] arr = doomed_assets.split(",");

		for(String s: arr){
			if(s.trim().length()>0){
			int id = Integer.valueOf(s);
			ContentAssetMapping map  = contentAssetMappingService.getMappingById(id);
			contentAssetMappingService.removeMapping(map);  
			}
		} 
		
		return "redirect:/"+request_url.replace("-", "/"); 
		
	}
	
	@RequestMapping(value="/upload/{moduleId}/{contentId}/{request_url}", method=RequestMethod.GET)
	public String upload(@PathVariable int moduleId, @PathVariable int contentId,  @PathVariable String request_url, Model model){ 
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		
		User user = (User) session.getAttribute("user");
		List<Asset> assets = assetService.getAssetByUserId(user.getId());
		
		logger.info("request url is " + request_url);	
		logger.info("Found "+assets.size());
		model.addAttribute("request_url", request_url);
		model.addAttribute("assets", assets);
		model.addAttribute("moduleId", moduleId);
		model.addAttribute("contentId", contentId);

		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/shared/upload";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String do_login(@RequestParam("email") String email, @RequestParam("password") String password, Model model){
	
		User user = userService.getUserByEmailAndPassword(email, password);
		
		if(user==null){
			model.addAttribute("user", new User());
			model.addAttribute("prefs", Prefs.getInstance());
			model.addAttribute("login_error", "Incorrect Password or Email");
			return "frontend/login";
		} 
	 
		session.setAttribute("user",  user);  
		return "redirect:/"; 
	}
 
	@RequestMapping(value="/logout")
	public String logout(){
		session.removeAttribute("user");
		return "redirect:/shared/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model){ 
		model.addAttribute("prefs", Prefs.getInstance());
		model.addAttribute("user", new User());
		return "frontend/login";
	}
	
	
	
	@RequestMapping(value="/sign_up", method=RequestMethod.POST)
	public String sign_up(@Valid User user, BindingResult result, Model model){
		
		if(!user.getPassword().equals(user.getConfirm_password())){
			result.rejectValue("password", "user.password", "The Password and its confirmation do not match");
		}
		if(result.hasErrors()){
			logger.info("errors exist");
			return "frontend/login";
		}
		
		userService.addUser(user);
		session.setAttribute("user", user);
	
		return "redirect:/";
		
		
		
	}
	@ResponseBody
	@RequestMapping(value="/loggedIn")
	public String loggedIn(){  
		return "LoggedIN";
	}
	 
	
	

	
	
	

	@ResponseBody
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload_post(@RequestParam("asset") MultipartFile file,  HttpServletRequest request){ 
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		
		Validator  validator = new AssetValidator(file);
		
		//parameters we shall return 
		String params = "0, 0";
		if(validator.validate()){ 
			 
				try{
				//set file name 
				User user = (User) session.getAttribute("user");
				String name =getRandomName(file.getOriginalFilename()) + "."+ getExtension(file.getOriginalFilename());
				java.io.File path = new java.io.File(Prefs.getInstance().getUploadDirectory(request)+java.io.File.separator + name);
				byte[] bytes = file.getBytes(); 
				BufferedOutputStream buff = new BufferedOutputStream(new FileOutputStream(path));
				buff.write(bytes);
				buff.close();
				  
				Asset a = new Asset();
				a.setLocation(path.getAbsolutePath());
				a.setName(name); 
				a.setUserId(user.getId());
				assetService.addAssert(a);
				 
				logger.info("saved " + a.getName());
				params = "1, "+a.getId();
				
				}
				catch(IOException es){ 
				}
			} 
		   
		String result_ = "<script type='text/javascript'> window.top.window.stopUpload("+params+");  </script>";
		logger.info("result is "+result_);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result_;
	} 	

	
	//@Todo Larry, why exactly did you create this method?
	@RequestMapping(value="/add_image/{contentId}/{modul}/{request_url}")
	public String add_image(@PathVariable int contentId, @PathVariable String modul, @PathVariable String request_url,  Model model){
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login";  
		 
		Module module = Module.UNKNOWN;
		module.setController(modul);
		return ("redirect:/shared/upload/"+module.toInt()+"/"+contentId+"/"+request_url);
		
	}
	
	
	@RequestMapping(value="/serve_image/{id}", method= RequestMethod.GET)
	public void serveImage(@PathVariable("id") String id, HttpServletResponse response){

		Asset s = assetService.getAssetById(Integer.valueOf(id));
		
		try{
		FileInputStream stream = new FileInputStream(new java.io.File(s.getLocation()));
		
		IOUtils.copy(stream, response.getOutputStream());
		stream.close();
		}
		catch(IOException es){
			
		}
		
	}
	 
	@RequestMapping(value="/map_assets", method=RequestMethod.POST)
	public String map_asset(@RequestParam("request_url") String url, @RequestParam("assets") String assets, @RequestParam("moduleId") String moduleId, @RequestParam("contentId") String contentId){
		 
		logger.info("received assets " + assets + " module "+moduleId + " content " + contentId);
 
		ContentAssetMapping map;
		String[] strs = assets.split(",");
		
		for(String s: strs){
			try{
				int assetId = Integer.valueOf(s.trim());
				if(assetId>0){
				int mId = Integer.valueOf(moduleId.trim());
				int cid = Integer.valueOf(contentId);
				
				//if this is user asset (Profile pic) simple change the asset id
				Module module = Module.UNKNOWN;
				module.setController("user");
				if(mId==module.toInt()){ 
					 
					User user = userService.getUserById(cid); 
					user.setAssetId(assetId);  
					userService.updateUser(user);
					break;
				}
				else{
				
				 map = new ContentAssetMapping();
				 map.setAssetId(assetId);
				 map.setContentId(cid);
				 map.setModuleId(mId);
				 contentAssetMappingService.addMapping(map);
				}
				}
			}
			catch(NumberFormatException es){
				continue;
			}
			catch(Exception es){
				continue;
			}
		}
		//retrieve the url that sent this request
		return "redirect:/"+url.replace("-", "/");
	}
	
	 
private String getExtension(String file){
	logger.info("File to get extension : " +file);
	
	StringBuilder build = new StringBuilder(); 
	int i = file.length()-1;
	char c = ' ';
	while(c!='.'){ 
		c = file.charAt(i);
		if(c =='.') break; 
		build.insert(0, c);
		i--;
	} 
	
	logger.info("Resulting extension " + build.toString()); 
	return build.toString();
}


private String getRandomName(String seed){
	
	logger.info("received " + seed);
	
	ArrayList<Character> arr = new ArrayList<Character>();
	
	for(char c: seed.toCharArray()){
		
		arr.add(c);
	}
	
	Collections.shuffle(arr);
	StringBuilder sb = new StringBuilder();
	
	for(char c: arr){
		sb.append(c);
	}
	String result  = sb.toString();
	logger.info("Converted to " + result);
	return result;
}
}