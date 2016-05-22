package com.larisoft.crackedbytes_;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.larisoft.crackedbytes_.Shared.DAO.ContentAssetMappingDao;
import com.larisoft.crackedbytes_.Shared.Model.Asset;
import com.larisoft.crackedbytes_.Shared.Model.Comment;
import com.larisoft.crackedbytes_.Shared.Model.ContentAssetMapping;
import com.larisoft.crackedbytes_.Shared.Model.Module;
import com.larisoft.crackedbytes_.Shared.Model.Prefs;
import com.larisoft.crackedbytes_.Shared.Service.AssetService;
import com.larisoft.crackedbytes_.Shared.Service.CommentService;
import com.larisoft.crackedbytes_.Shared.Service.ContentAssetMappingService;
import com.larisoft.crackedbytes_.Shared.Service.MessageService;
import com.larisoft.crackedbytes_.User.Model.User;
import com.larisoft.crackedbytes_.blog.Model.Article;
import com.larisoft.crackedbytes_.blog.Service.ArticleService;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

	Logger logger = LoggerFactory.getLogger(BlogController.class);
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	AssetService assetService;
	
	@Autowired
	MessageService messageService;
	
	Module module = Module.BLOG;
	
	@Autowired
	HttpSession session;
	
	ContentAssetMappingService contentAssetMappingService;


	
	@Autowired
	@Qualifier("contentAssetMappingService")
	public void setContentAssetMapping(ContentAssetMappingService dao){
		this.contentAssetMappingService = dao;
	}
	
	@RequestMapping(value="/")
	public String home(Model model){
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
 		return "backend/blog/about";
	}
	
	
	@RequestMapping(value="/edit_article/{id}")
	public String edit_article(Model model, @PathVariable int id){  
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		 
		User user = (User) session.getAttribute("user");
		
		java.util.List<Asset> assets = assetService.getAssetByUserId(user.getId());
		  
		Article article = articleService.getArticleById(id);
		String this_url = "blog/edit_article/"+id;
		model.addAttribute("this_url", this_url.replace("/", "-"));
		model.addAttribute("assets", assets);
		model.addAttribute("article", article);
		model.addAttribute("section", "blog");
		model.addAttribute("sub_section", "article");
		model.addAttribute("id", article.getId());

		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/blog/new_article";	
		
	}
	
	
	@RequestMapping(value="/new_article", method=RequestMethod.GET)
	public String new_article(Model model){  
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		Article article = new Article();
		
		User user = (User) session.getAttribute("user");  
		  
		
		List<Asset> assets = assetService.getAssetByUserId(user.getId());
		
		article.setAuthorId(user.getId()); 
		model.addAttribute("messageService", messageService);
		model.addAttribute("article", article);
		model.addAttribute("assets", assets);
		model.addAttribute("section", "blog");
		model.addAttribute("user", user);
		model.addAttribute("sub_section", "article");

		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/blog/new_article";
	}
	
	@RequestMapping(value="/new_article/add", method=RequestMethod.POST)
	public String new_article_add(@Valid Article article, BindingResult result, Model model){  
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login";
		
		//verify if this title already exists in case of new articles
		boolean title_conflicts = title_conflicts(article.getTitle()); 
		if(title_conflicts && article.getId()==0){ 
			result.addError(new ObjectError("title", "Article already exists!"));
		}
		
		if(result.hasErrors()){
		model.addAttribute("article", article);
		model.addAttribute("section", "blog");

		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/blog/new_article";
		}
		else{
			
			if(article.getId()>0){ 
				articleService.updateArticle(article);
			}
			else
			{ 
		 
			articleService.addArticle(article);
			
			}
			return "redirect:/blog/edit_article/"+article.getId();
		}
		
	}
	
	
	@RequestMapping(value="/view_articles", method=RequestMethod.GET)
	public String view_articles(Model model){  
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		 
		User userer =  (User) session.getAttribute("user");
		int authorId = userer.getId();
		
		ArrayList<Article> articles =  (ArrayList<Article>) articleService.getArticlesByAuthor(authorId);
  
		model.addAttribute("articles", articles);
		model.addAttribute("section", "blog");
		model.addAttribute("sub_section", "article");

		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/blog/articles_list";
	}
	
	
	@RequestMapping(value="/view_article/{identity}", method=RequestMethod.GET)
	public String view_article(Model model, @PathVariable int identity){  
		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login";

		java.util.List<ContentAssetMapping> assets = contentAssetMappingService.getMappingsFor(module.toInt(), identity);
		
		for(ContentAssetMapping asset: assets){
			logger.info("Asset iD " + asset.getAssetId());
		}
		Article article = articleService.getArticleById(identity); 
		article.setViews(article.getViews()+1);
		articleService.updateArticle(article); 
		model.addAttribute("assets", assets);
		model.addAttribute("article", article);
		model.addAttribute("section", "blog");
		model.addAttribute("sub_section", "article");
		
		List<Comment> comments = commentService.getCommentsFor(article.getId(), module.toInt());
		
		logger.info("comments size " + comments.size());
		model.addAttribute("comments", comments);

		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/blog/view_article";
	}
	
	
	private boolean title_conflicts(String title){
		//we check if the friendly url already exists. IF it does, then this title is taken
		String friendly_url = title.toLowerCase().replace(" ", "-");
		
		Article found = articleService.getArticleByUrl(friendly_url);
		
		if(found==null) return false;
		
		return true;
	}
	
	
}
