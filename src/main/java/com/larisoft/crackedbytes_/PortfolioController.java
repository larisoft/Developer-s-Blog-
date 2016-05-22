package com.larisoft.crackedbytes_;

/*
 * this is the app module for apps written by the owner of this system
 * note that it is also reffered to as Portfolio.
 * 
 */
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.larisoft.crackedbytes_.Shared.DAO.ContentAssetMappingDao;
import com.larisoft.crackedbytes_.Shared.Model.Asset;
import com.larisoft.crackedbytes_.Shared.Model.ContentAssetMapping;
import com.larisoft.crackedbytes_.Shared.Model.Module;
import com.larisoft.crackedbytes_.Shared.Model.Prefs;
import com.larisoft.crackedbytes_.Shared.Service.AssetService;
import com.larisoft.crackedbytes_.Shared.Service.ContentAssetMappingService;
import com.larisoft.crackedbytes_.Shared.Service.ContentAssetMappingServiceImpl;
import com.larisoft.crackedbytes_.User.Model.User;
import com.larisoft.crackedbytes_.portfolio.Model.App;
import com.larisoft.crackedbytes_.portfolio.Model.Category;
import com.larisoft.crackedbytes_.portfolio.Model.Platform;
import com.larisoft.crackedbytes_.portfolio.Model.Validate_file_upload;
import com.larisoft.crackedbytes_.portfolio.Service.AppService;
import com.larisoft.crackedbytes_.portfolio.Service.CategoryService;
import com.larisoft.crackedbytes_.portfolio.Service.PlatformService;

@Controller
@RequestMapping(value="/portfolio")

public class PortfolioController{	 
	org.slf4j.Logger logger = LoggerFactory.getLogger(PortfolioController.class);
	
	AppService  appService;
	PlatformService platformService;
	CategoryService categoryService; 
	AssetService assetService;
	HttpSession session;
	
	ContentAssetMappingService contentAssetMappingService;
	//section of the backend we are in
	Module module = Module.APP;
	
	
	@Autowired
	@Qualifier("contentAssetMappingService")
	public void setContentAssetMapping(ContentAssetMappingService dao){
		this.contentAssetMappingService = dao;
	}
	
	@Autowired
	public void setHttpSession(HttpSession sessio){
		this.session = sessio;
	}
	
	@Autowired
	@Qualifier(value="assetService")	
	public void setAssetService(AssetService assetService){
		this.assetService = assetService;
	}
	
	 
	
	@Autowired
	@Qualifier(value="platformService")
	public void setPlatformService(PlatformService platform){
		this.platformService = platform;
	}
	
	@Autowired
	@Qualifier(value="appService")
	public void setAppService(AppService appService){ 
		this.appService = appService;
		logger.info("set service");
	}
	
	@Autowired
	@Qualifier(value="categoryService")
	public void setCategoryService(CategoryService categoryService){
		this.categoryService = categoryService;
	}
	
	
	//home 
	@RequestMapping(value="", method= RequestMethod.GET)
	public String home(Model model){
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login";  
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		model.addAttribute("module", module );
		model.addAttribute("prefs", Prefs.getInstance());
		return "/backend/portfolio/about";
	}
	 
	
	//Category
	@RequestMapping(value="delete_category/{id}")
	public String delete_category(@PathVariable int id, Model model){ 
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		Category category = categoryService.getCategory(id);
		categoryService.removeCategory(category); 
		return "redirect:/portfolio/view_categories"; 
	}
	
	
	@RequestMapping(value="view_categories", method=RequestMethod.GET)
	public String view_category(Model model){ 

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		List<Category> p = categoryService.listCategory();
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		model.addAttribute("module", module);
		model.addAttribute("categories", p);
		model.addAttribute("sub_section", "category");
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/portfolio/categories_list";
		
	}
	
	
	@RequestMapping(value="edit_category/{id}", method=RequestMethod.GET)
	public String edit_category(Model model, @PathVariable int i){

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login";  
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		Category c = categoryService.getCategory(i); 
		model.addAttribute("category", c);
		model.addAttribute("module", module); 
		model.addAttribute("sub_section", "category");
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/portfolio/new_category";
		
		
	}
 
	@RequestMapping(value="new_category", method=RequestMethod.GET)
	public String new_category(Model model){  

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		model.addAttribute("category", new Category());
		model.addAttribute("module", module); 
		model.addAttribute("sub_section", "category"); 
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/portfolio/new_category";
	}
	

	@RequestMapping(value="new_category/add", method=RequestMethod.POST)
	public String new_category_post(@Valid Category category, BindingResult result, Model model){ 

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		
		if(result.hasErrors()){
			
		model.addAttribute("category", category);
		model.addAttribute("module", module); 
		model.addAttribute("sub_section", "category"); 
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/portfolio/new_category";
		}
		else{
			
			if(category.getId()== 0){
			//inserting new
			categoryService.addCategory(category);
			 
			category.setAuthorID(user.getId());
			}
			else{
				//updating existing
				categoryService.updateCategory(category);
			}
			return "redirect:/portfolio/view_categories";
		}
	}
	
	
	//Platforms 
	@RequestMapping(value="delete_platform/{id}")
	public String delete_platform(@PathVariable int id, Model model){

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login";
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		Platform platform = platformService.getPlatformById(id);
		platformService.remove(platform); 
		return "redirect:/portfolio/view_platforms";
		
	}
	
	
	@RequestMapping(value="view_platforms", method=RequestMethod.GET)
	public String view_platforms(Model model){ 

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		List<Platform> p = platformService.listPlatforms();
		model.addAttribute("module", module);
		model.addAttribute("platforms", p);
		model.addAttribute("sub_section", "platform");
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/portfolio/platform_list";
		
	}
	
	
	@RequestMapping(value="edit_platform/{id}", method=RequestMethod.GET)
	public String edit_platform(Model model, @PathVariable int id){

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		Platform p = platformService.getPlatformById(id); 
		model.addAttribute("platform", p);
		model.addAttribute("module", module);
		model.addAttribute("sub_section", "platform");
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/portfolio/new_platform";
		
		
	}
 
	@RequestMapping(value="new_platform", method=RequestMethod.GET)
	public String new_platform(Model model){ 
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		model.addAttribute("platform", new Platform());
		model.addAttribute("module", module);
		model.addAttribute("prefs", Prefs.getInstance());
		model.addAttribute("sub_section", "platform");
		
		return "backend/portfolio/new_platform";
	}
	

	@RequestMapping(value="new_platform/add", method=RequestMethod.POST)
	public String new_platform_post(@Valid Platform platform, BindingResult result, Model model){

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		if(result.hasErrors()){
		model.addAttribute("platform", platform);
		model.addAttribute("module", module);
		model.addAttribute("prefs", Prefs.getInstance());
		model.addAttribute("sub_section", "platform"); 
		return "backend/portfolio/new_platform";
		}
		else{
			
			if(platform.getId()== 0){
			//inserting new
			
			platformService.insert(platform);
			}
			else{
				//updating existing
				platformService.update(platform);
			}
			return "redirect:/portfolio/view_platforms";
		}
	}
	
	//APPS
	
	@RequestMapping(value="/publish/{id}")
	public String publish_app(@PathVariable int id){
		
		return "redirect:/portfolio/view_app/"+id;	
	}
	@RequestMapping(value="/new_app", method = RequestMethod.GET)
	public String upload(Model model){   

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		List<Asset> assets = assetService.getAssetByUserId(user.getId());
		
		App app = new App(); 
		
		app.setAuthorId(user.getId());
		model.addAttribute("title", "New App");
		model.addAttribute("app", app); 
		model.addAttribute("module", module);
		model.addAttribute("sub_section", "app");
		model.addAttribute("assets", assets);
		model.addAttribute("prefs", Prefs.getInstance());
		model.addAttribute("platforms", getSelectOptionsForPlatforms(platformService.listPlatforms()));   
		model.addAttribute("categories", getSelectOptionsForCategory(categoryService.listCategory()));    
		return "/backend/portfolio/new_file";
	}
	
	
	@RequestMapping(value="/edit_app/{id}", method = RequestMethod.GET)
	public String edit_app(@PathVariable("id") int  id, Model model){ 

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		App app = appService.getAppById(id) 	; 
	 	String this_url = "portfolio/edit_app/"+id;
	 	
	 	User user = (User) session.getAttribute("user");
	 	
	 	List<Asset> assets = assetService.getAssetByUserId(user.getId());
		model.addAttribute("user");
	 	
	 	model.addAttribute("this_url", this_url.replace("/", "-"));
		model.addAttribute("title", "Edit "+app.getTitle());
		model.addAttribute("module", module);
		model.addAttribute("sub_section", "manage_app");
		model.addAttribute("assets", assets);
		model.addAttribute("app", app);
		model.addAttribute("prefs", Prefs.getInstance());
		model.addAttribute("platforms", getSelectOptionsForPlatforms(platformService.listPlatforms()));   
		model.addAttribute("categories", getSelectOptionsForCategory(categoryService.listCategory())); 
		return "backend/portfolio/new_file"; 
	}
	
	
	@RequestMapping(value="/new_file/add", method= RequestMethod.POST)
	public String new_file (@Valid App app, BindingResult result, Model model){  

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		List<Asset> assets = assetService.getAssetByUserId(user.getId()); 
		model.addAttribute("assets", assets);
		
		if(result.hasErrors()){       
			model.addAttribute("module", module);
			model.addAttribute("categories", getSelectOptionsForCategory(categoryService.listCategory()));
			model.addAttribute("platforms", getSelectOptionsForPlatforms(platformService.listPlatforms())); 
			model.addAttribute("prefs", Prefs.getInstance());
			model.addAttribute("sub_section", "manage_app");
			return "backend/portfolio/new_file";	
		} 
		logger.info("Id here is " + app.getId());
		int id = 0;
		if(app.getId()>0){
			if(app.update_validate()){
			appService.updateApp(app);
			id = app.getId();
			}
			else{
				//contains errors
				model.addAttribute("module", module);
				model.addAttribute("sub_section", "manage_app");
				model.addAttribute("categories", getSelectOptionsForCategory(categoryService.listCategory()));
				model.addAttribute("platforms", getSelectOptionsForPlatforms(platformService.listPlatforms())); 
				model.addAttribute("file", app);
				model.addAttribute("prefs", Prefs.getInstance());
				return "backend/portfolio/new_file";	
			}
		}
		else{
		 
			app.setAuthorId(user.getId());
			id = appService.addApp(app); 
		}
		return("redirect:/portfolio/edit_app/"+id); 
		
	}
	 
	
	
	
	@RequestMapping(value="/skip_upload/{projectId}")
	public String skip_upload(@PathVariable("projectId") int projectId, Model model){ 
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		if(assetService.getAssetByProjectId(Integer.valueOf(projectId)).size() > 0){ 
			return("redirect:/portfolio/view_app/"+projectId);
		} 
		
		List<String> errors = new ArrayList<String>();
		errors.add("You must provide four distinct pictures of your app");			
		model.addAttribute("errors", errors);
		model.addAttribute("section", "platform");
		model.addAttribute("sub_section", "manage_app");
		model.addAttribute("id", projectId); 
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/portfolio/upload_file";
	
	}
	
	
	@RequestMapping(value="/upload_file/{id}", method = RequestMethod.GET)
	public String upload_file(@PathVariable("id") int  id, Model model){

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		App f = appService.getAppById(id); 
		model.addAttribute("id", id);
		model.addAttribute("title", "App Assets");
		model.addAttribute("errors", "");
		model.addAttribute("module", module);
		model.addAttribute("sub_section", "manage_app");
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/portfolio/upload_file";
	}
	
	
	@RequestMapping(value="/view_apps")
	public String view_apps( Model model){   
		
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login";  
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		 
		int id = user.getId();
		List<App> apps = appService.getAppsByAuthor(id);  
		model.addAttribute("apps", apps);
		model.addAttribute("module", module);
		model.addAttribute("prefs", Prefs.getInstance());
		model.addAttribute("sub_section", "manage_app");
		return "backend/portfolio/apps_list"; 
	}
	
	
	
	 
	
	@RequestMapping(value="/view_app/{id}")
	public String view(Model model, @PathVariable("id") int id){   
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login";  
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user");
		
		App f = appService.getAppById(id);
		String platform = platformService.getPlatformById(f.getPlatformId()).getTitle();
		model.addAttribute("app", f);
	
		//set platform string name
		model.addAttribute("platform", platform); 
		model.addAttribute("assets", contentAssetMappingService.getMappingsFor(module.toInt(), id));
		//set title of the page.
		model.addAttribute("module", module);
		model.addAttribute("title",   f.getTitle()); 
		model.addAttribute("prefs", Prefs.getInstance());
		model.addAttribute("sub_section", "manage_app");
		return "backend/portfolio/view_app";
	}
	
	 
	
	
	private HashMap<Integer, String> getSelectOptionsForCategory(List<Category> categories){  
		HashMap<Integer, String> map2 = new HashMap<Integer, String>(); 
		map2.put(0, " ");
		for(Category c: categories){
			
			map2.put(c.getId(), c.getTitle()); 
		}
		return map2;
	}
	
	
	private HashMap<Integer, String> getSelectOptionsForPlatforms(List<Platform> platforms){ 
		//prepare the content of the platform select control 
				HashMap<Integer, String> map = new HashMap<Integer, String>();
				map.put(0, " ");
				for(Platform p : platforms){
					map.put(p.getId(),p.getTitle());
				}  
				
				return map;  
	}
	
	
	
	
	
	
	}
	
	 

