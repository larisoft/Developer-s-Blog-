	package com.larisoft.crackedbytes_;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.larisoft.crackedbytes_.Shared.Model.Prefs;
import com.larisoft.crackedbytes_.User.Model.User;
import com.larisoft.crackedbytes_.bug.Dao.BugDAO;
import com.larisoft.crackedbytes_.bug.Model.Bug;
import com.larisoft.crackedbytes_.bug.Service.BugService;
import com.larisoft.crackedbytes_.bug.Service.BugServiceImpl;

@Controller
@RequestMapping("/bug")
public class BugController {
 
	String section = "bug_section"; 
	BugService bugService;
	HttpSession session;
	
	
	@Autowired
	public void setHttpSession(HttpSession s){
		session = s;
	}
	
	@Autowired
	public void setBugService(BugService bugService){ 
		this.bugService = bugService;
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String bug(Model model){ 

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		model.addAttribute("section", section); 
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/bug/about";
	}
	
	@RequestMapping(value="/view_bugs", method=RequestMethod.GET)
	public String view_bugs(Model model){

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		User user = (User) session.getAttribute("user");
		int authorId = user.getId();
		List<Bug> bugs = bugService.listBugs(authorId);
		model.addAttribute("bugs", bugs);
		model.addAttribute("section", section); 
		model.addAttribute("sub_section", "manage_bugs");
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/bug/bugs_list";
	}
	
	@RequestMapping(value="/edit_bug/{id}", method=RequestMethod.GET)
	public String edit_bug(Model model, @PathVariable int id){

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		Bug b = bugService.getBugById(id);
		model.addAttribute("bug", b);
		model.addAttribute("section", section);
		model.addAttribute("sub_section", "manage_bugs"); 
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/bug/new_bug";
	}
	
	@RequestMapping(value="/view_bug/{id}", method=RequestMethod.GET)
	public String view_bug(Model model, @PathVariable int id){

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		Bug b = bugService.getBugById(id);
		b.setViews(b.getViews()+1);
		bugService.updateBug(b);
		model.addAttribute("bug", b);
		model.addAttribute("section", section);
		model.addAttribute("sub_section", "manage_bugs"); 
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/bug/view_bug";
	}

	

	@RequestMapping(value="/new_bug", method=RequestMethod.GET)
	public String new_bug(Model model){

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		model.addAttribute("bug", new Bug());
		model.addAttribute("section", section);
		model.addAttribute("sub_section", "manage_bugs"); 
		model.addAttribute("prefs", Prefs.getInstance());
		return "backend/bug/new_bug";
	}
	
	@RequestMapping(value="/new_bug/add", method=RequestMethod.POST)
	public String new_bug_post(Model model, @Valid Bug bug, BindingResult result){

		if(!Prefs.isLoggedIn(session)) return "redirect:/shared/login"; 
		User user = (User) session.getAttribute("user");
		if(result.hasErrors()){ 
			return "backend/bug/new_bug";
		} 
		if(bug.getId()==0){
		bug.setAuthorId(user.getId());
		bugService.addBug(bug);
		}
		else{ 
		bugService.updateBug(bug);
		}
		
		return "redirect:/bug/view_bugs/";
}
}
