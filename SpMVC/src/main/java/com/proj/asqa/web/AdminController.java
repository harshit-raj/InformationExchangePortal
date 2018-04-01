package com.proj.asqa.web;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.asqa.domain.Answer;
import com.proj.asqa.domain.UserRole;
import com.proj.asqa.domain.Usr;
import com.proj.asqa.service.QuestionService;
import com.proj.asqa.service.UsrService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UsrService usrService;
	
	@Autowired
	private QuestionService questService;
	
	@RequestMapping(value = "/viewUsers")
	public String viewUsers(Model model,Principal principal) {
		model.addAttribute("userList",usrService.listUsr());
		model.addAttribute("usr",usrService.getUserbyEmail(principal.getName()));
		return "viewUsers";
	}
	
	@RequestMapping(value = "/makeMod/{id}")
	public String makeMod(Model model,Principal principal,@PathVariable("id") long id) {
		Usr usr = usrService.getUser(id);
		UserRole role = new UserRole();
		role.setRole("ROLE_MOD");
		role.setUser(usr);
	
		//role.setRole("ROLE_ADMIN");//do it by getting input from user
		//role.getUser().add(usr);
		usr.getRole().add(role);
		usrService.saveorUpdate(usr);
		
		//model.addAttribute("userList",usrService.listUsr());
		//model.addAttribute("usr",usrService.getUserbyEmail(principal.getName()));
		return "redirect:/admin/viewUsers";
	}
	
	
}
