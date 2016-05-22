package com.larisoft.crackedbytes_;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.larisoft.crackedbytes_.Shared.Model.Comment;
import com.larisoft.crackedbytes_.Shared.Model.Message;
import com.larisoft.crackedbytes_.Shared.Model.Module;
import com.larisoft.crackedbytes_.Shared.Model.Prefs;
import com.larisoft.crackedbytes_.Shared.Service.CommentService;
import com.larisoft.crackedbytes_.Shared.Service.ContentAssetMappingService;
import com.larisoft.crackedbytes_.Shared.Service.MessageService;
import com.larisoft.crackedbytes_.User.Model.User;
import com.larisoft.crackedbytes_.User.Service.UserService;
import com.larisoft.crackedbytes_.blog.Model.Article;
import com.larisoft.crackedbytes_.blog.Service.ArticleService;
import com.larisoft.crackedbytes_.bug.Model.Bug;
import com.larisoft.crackedbytes_.bug.Service.BugService;
import com.larisoft.crackedbytes_.portfolio.Model.App;
import com.larisoft.crackedbytes_.portfolio.Service.AppService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	int page_items_size = 10; 
	int home_page_items_size = 3;
	@Autowired
	ArticleService articleService;
	
	@Autowired
	AppService appService;
	
	@Autowired
	@Qualifier(value="bugService") 
	BugService bugService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;
	
	
	@Autowired
	MessageService messageService;
	
	
	@Autowired
	@Qualifier(value="contentAssetMappingService")
	ContentAssetMappingService contentAssetMappingService;

	
	@Autowired
	HttpSession session;
	
	
	
	@ResponseBody
	@RequestMapping("/send_message")
	public String send_message(@RequestParam("receiver") int receiver_id, @RequestParam("email") String sender, @RequestParam("message") String message)
	{
		Message messag = new Message();
		messag.setMessage(message);
		messag.setMessage_read(0);
		messag.setEmail(sender);
		messag.setReceiver_id(receiver_id);
		messageService.addMessage(messag);
		return "Message Sent. This Member will reply you soon!";
		
	}
	
	
	@RequestMapping(value="user/{username}")
	public String view_user(@PathVariable("username") String username, Model model){ 
		User user  = userService.getUserByUrl(username); 
		model.addAttribute("user_profile", user); 
		model.addAttribute("prefs", Prefs.getInstance()); 
		return "frontend/user";
		 
	}
	 
	
	@RequestMapping(value="search_general", method = RequestMethod.POST)
	public String search_general(@RequestParam("query") String query,  Model model){
		if(query.trim().length() <2) return "redirect:/";
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		List<Article> articles = articleService.searchFor(query,0,  home_page_items_size);
		List<Bug> bugs = bugService.searchFor(query, 0,  home_page_items_size);
		List<App> apps = appService.searchFor(query, 0,  home_page_items_size);
		logger.info("found search" + articles.size());
		model.addAttribute("contentAssetMappingService", contentAssetMappingService);
		model.addAttribute("userService", userService);
		
		model.addAttribute("prefs", Prefs.getInstance());
		model.addAttribute("articles", articles);
		model.addAttribute("apps", apps);
		model.addAttribute("bugs", bugs);
		model.addAttribute("query", query);
		
		return "frontend/search_general";
	}
	
	
	
	@RequestMapping(value="search/app/{query}/{index}", method = RequestMethod.GET)
	public String search_app(@PathVariable("query") String query, @PathVariable("index") int index,  Model model){
		  
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		List<App> articles = appService.searchFor(query,(index-1) * page_items_size,  page_items_size); 
		List<App> next = appService.searchFor(query, ((index+1)-1) * page_items_size, page_items_size);
	
		if(next.size()<1){
			model.addAttribute("has_more", "false");
		}
		
		else{
			model.addAttribute("has_more", "true");
		}
		model.addAttribute("contentAssetMappingService", contentAssetMappingService);
		model.addAttribute("userService", userService);
		model.addAttribute("next_page", index+1);
		model.addAttribute("module", Module.APP.toString());
		model.addAttribute("prefs", Prefs.getInstance());
		model.addAttribute("contents", articles); 
		model.addAttribute("query", query);
		
		return "frontend/search_app";
	}
			 
	
	@RequestMapping(value="search/bug/{query}/{index}", method = RequestMethod.GET)
	public String search_bug(@PathVariable("query") String query, @PathVariable("index") int index,  Model model){
		 
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		logger.info("index is " + index);
		int next_index = index+1;
		
		List<Bug> articles = bugService.searchFor(query,(index-1) * page_items_size,  page_items_size); 
		List<Bug> next = bugService.searchFor(query, (next_index-1) * page_items_size, page_items_size);
		
		logger.info("next has size " + next.size());
		if(next.size()<1){
			model.addAttribute("has_more", "false");
		}
		
		else{
			model.addAttribute("has_more", "true");
		}
		model.addAttribute("contentAssetMappingService", contentAssetMappingService);
		model.addAttribute("userService", userService);
		model.addAttribute("next_page", index+1);
		model.addAttribute("module", Module.BUG.toString());
		model.addAttribute("prefs", Prefs.getInstance());
		model.addAttribute("contents", articles); 
		model.addAttribute("query", query);
		
		return "frontend/search_bug";
	}
			 
	
	@RequestMapping(value="search/blog/{query}/{index}", method = RequestMethod.GET)
	public String search_article(@PathVariable("query") String query, @PathVariable("index") int index,  Model model){
		 
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		List<Article> articles = articleService.searchFor(query,(index-1) * page_items_size,  page_items_size); 
		List<Article> next = articleService.searchFor(query, ((index+1)-1) * page_items_size, page_items_size);
	
		if(next.size()<1){
			model.addAttribute("has_more", "false");
		}
		
		else{
			model.addAttribute("has_more", "true");
		}
		model.addAttribute("contentAssetMappingService", contentAssetMappingService);
		model.addAttribute("userService", userService);
		model.addAttribute("next_page", index+1);
		model.addAttribute("module", Module.BLOG.toString());
		model.addAttribute("prefs", Prefs.getInstance());
		model.addAttribute("contents", articles); 
		model.addAttribute("query", query);
		
		return "frontend/search_article";
	}
	 
	  
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) { 
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		
		List<Article> articles = articleService.getLatest(0,  home_page_items_size);  
		List<App> apps = appService.getLatest(0, home_page_items_size);  
		List<Bug> bugs = bugService.getLatest(0, home_page_items_size);   
		   
		model.addAttribute("contentAssetMappingService", contentAssetMappingService);
		model.addAttribute("userService", userService);
		model.addAttribute("articles", articles);
		model.addAttribute("apps", apps);
		model.addAttribute("bugs", bugs);
		model.addAttribute("user", user);
		model.addAttribute("prefs", Prefs.getInstance());
		
		
		return "frontend/index";
	} 
	
	@RequestMapping(value = "/blog/{index}", method = RequestMethod.GET)
	public String article(Model model, @PathVariable("index") int index) { 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		
		List<Article> articles = articleService.getLatest((index-1) * page_items_size,   page_items_size);
		List<Article> next_page = articleService.getLatest(((index+1)-1)*page_items_size,  page_items_size );
		
		if(next_page.size()>0){
		model.addAttribute("has_more", true);
		model.addAttribute("index", index+1); 
		}
		else{ 
		 model.addAttribute("has_more", false);
		}
		model.addAttribute("userService", userService);
		model.addAttribute("contents", articles);
		model.addAttribute("items_label", "Articles");
		model.addAttribute("module", Module.BLOG.toString());
		model.addAttribute("next_page", index+1);

		model.addAttribute("prefs", Prefs.getInstance());
		return "frontend/list_content"; 
	} 
	
	@RequestMapping(value = "/app/{index}", method = RequestMethod.GET)
	public String app(Model model, @PathVariable("index") int index) { 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		
		List<App> apps = appService.getLatest((index-1) * page_items_size,   page_items_size);
		List<App> next_page = appService.getLatest(((index+1)-1)*page_items_size,  page_items_size );
		model.addAttribute("contentAssetMappingService", contentAssetMappingService);
		
		if(next_page.size()>0){
		model.addAttribute("has_more", true);
		model.addAttribute("index", index+1); 
		}
		else{
		 model.addAttribute("has_more", false);
		}
		model.addAttribute("userService", userService);
		model.addAttribute("contents", apps);
		model.addAttribute("items_label", "Apps");
		model.addAttribute("module", Module.APP.toString());
		model.addAttribute("next_page", index+1);

		model.addAttribute("prefs", Prefs.getInstance());
		return "frontend/list_content"; 
	} 
	
	
	@RequestMapping(value = "/bug/{index}", method = RequestMethod.GET)
	public String bug(Model model, @PathVariable("index") int index) { 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		
		List<Bug> bugs = bugService.getLatest((index-1) * page_items_size,   page_items_size);
		List<Bug> next_page = bugService.getLatest(((index+1)-1)*page_items_size,  page_items_size );
		model.addAttribute("contentAssetMappingService", contentAssetMappingService);
		
		if(next_page.size()>0){
		model.addAttribute("has_more", true);
		model.addAttribute("index", index+1); 
		}
		else{ 
		 model.addAttribute("has_more", false);
		}
		model.addAttribute("userService", userService);
		model.addAttribute("contents", bugs );
		model.addAttribute("items_label", "Bug Solutions");
		model.addAttribute("module", "bug");
		model.addAttribute("next_page", index+1);

		model.addAttribute("prefs", Prefs.getInstance());
		return "frontend/list_content"; 
	} 
	
	@RequestMapping(value="/upload", method=RequestMethod.GET)
	public String upload_asset(){
		
		return "backend/shared/upload";
	}
	 
	
	@RequestMapping(value="view/blog/{friendly_url}", method = RequestMethod.GET)
	public String read(@PathVariable("friendly_url") String url, Model model){ 
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		Article article = articleService.getArticleByUrl(url); 
		
		model.addAttribute("content", article); 
		
		Comment comment = new Comment();
		comment.setContent_id(article.getId());
		comment.setModule_id(Module.BLOG.toInt());
		comment.setUrl("/view/blog/"+url);
		model.addAttribute("comment", comment);
		
		Module module = Module.UNKNOWN;
		module.setController("blog");
		
		List<Comment> comments = commentService.getCommentsFor(article.getId(), module.toInt());
		
		logger.info("comments size " + comments.size());
		model.addAttribute("comments", comments);
		model.addAttribute("prefs", Prefs.getInstance());
		return "frontend/read";
	}
	 
	@RequestMapping(value="view/app/{friendly_url}", method = RequestMethod.GET)
	public String read_app(@PathVariable("friendly_url") String url, Model model){  
		
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		App app = appService.getAppByUrl(url);
		
		Comment comment = new Comment();
		comment.setContent_id(app.getId());
		comment.setModule_id(Module.APP.toInt());
		comment.setUrl("/view/app/"+url);
		Module module = Module.UNKNOWN;
		module.setController("app");
		List<Comment> comments = commentService.getCommentsFor(app.getId(), module.toInt());
		model.addAttribute("comments", comments); 
		model.addAttribute("comment", comment);
		
		
		model.addAttribute("content", app); 

		model.addAttribute("prefs", Prefs.getInstance());
		return "frontend/read";
	}
	 
	@RequestMapping(value="view/bug/{friendly_url}", method = RequestMethod.GET)
	public String read_bug(@PathVariable("friendly_url") String url, Model model){ 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		
		Bug bug = bugService.getBugByUrl(url); 
		Module module = Module.UNKNOWN;
		module.setController("bug");
		
		Comment comment = new Comment();
		comment.setContent_id(bug.getId());
		comment.setModule_id(Module.BUG.toInt());
		comment.setUrl("/view/bug/"+url);
		
		
		List<Comment> comments = commentService.getCommentsFor(bug.getId(), module.toInt());
		model.addAttribute("comments", comments);
		model.addAttribute("comment", comment);
		
		model.addAttribute("content", bug);

		model.addAttribute("prefs", Prefs.getInstance());
		
		return "frontend/read";
	}
	
	 
	@RequestMapping(value="view/user/{friendly_url}", method = RequestMethod.GET)
	public String read_person(@PathVariable("friendly_url") String url, Model model){ 
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user); 

		model.addAttribute("prefs", Prefs.getInstance());
		return user.getName();
	}
	
 
			
	
	
}
