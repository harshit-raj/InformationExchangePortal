package com.proj.asqa.web;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.proj.asqa.domain.Answer;
import com.proj.asqa.domain.Question;
import com.proj.asqa.domain.UserRole;
import com.proj.asqa.domain.Usr;
import com.proj.asqa.service.AnswerService;
import com.proj.asqa.service.QuestionService;
import com.proj.asqa.service.UsrService;



@Controller

public class UsrController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsrController.class);
	@Autowired
	private UsrService usrService;
	
	@Autowired
	private QuestionService quesService;
	
	@Autowired
	private AnswerService ansService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, Principal principal) {
		
		
		Usr user = usrService.getUserbyEmail(principal.getName());
		int q = user.getQuestion().size();
		int a = user.getAnswer().size();
		model.addAttribute("usr",user);
		model.addAttribute("qcount",q);
		model.addAttribute("acount",a);
		return "home";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String addUser(@Valid Usr usr,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "usrs";
		}
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(usr);
		usr.getRole().add(role);
		usrService.addUsr(usr);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String listUser(Model model, HttpSession session) {
		model.addAttribute(new Usr());
		return "usrs";
	}
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.GET)
	public String updateUser(Model model, HttpSession session,Principal principal) {
		Usr usr = usrService.getUserbyEmail(principal.getName());
		model.addAttribute("usr",usr);
		return "updateUser";
	}
	
	
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String update_User(@Valid Usr usr,BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "updateUser";
		}
		usrService.saveorUpdate(usr);
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/usrs/detailBind/{userID}")
	public String usertoSession(@PathVariable Long userID, HttpSession session) {
		
		session.setAttribute("use", usrService.getUser(userID));
		return "redirect:/ques/profile";
	}
	
	@RequestMapping(value = "/profile")
	public String userProfile(Model model, HttpSession session) {
		model.addAttribute("usr",usrService.getUser((Long) session.getAttribute("userId")));
		return "viewProfile";
		
	}
	
	   
	@RequestMapping(value = "addQuest",method = RequestMethod.POST)
	public String addQuestion(@Valid Question question,BindingResult bindingResult,Principal principal) {
		System.out.println("Entered post");
		if(bindingResult.hasErrors()) {
			System.out.println("Error thingy");
			return "addQuestion";
		}
		System.out.println(question);
		Usr user = usrService.getUserbyEmail(principal.getName());
		user.getQuestion().add(question);
		question.setUser(user);
		usrService.addUsr(user);
		System.out.println("Return thingy");
		return "redirect:/addQuest";
	}
	
	@RequestMapping(value = "/addQuest",method = RequestMethod.GET)
	public String addQuestion(Model model, Principal principal) {
		Usr user = usrService.getUserbyEmail(principal.getName());
		Question question = new Question();
		question.setUser(user);
		
		model.addAttribute(question);
		model.addAttribute(user);
		System.out.println("From Get");
		return "addQuestion";
	}
	
	@RequestMapping(value = "/loginSuccess")
	public String loginSucess(Principal principal) {
		System.out.println(principal.getName());
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/viewProfile")
	public String viewProfile(Principal principal, Model model) {
		Usr user = usrService.getUserbyEmail(principal.getName());
		model.addAttribute("usr",user);
		return "viewProfile";
	}
	
	@RequestMapping(value = "/viewProfile/{id}")
	public String viewUserProfile(Principal principal, Model model,@PathVariable long id) {
		Usr user = usrService.getUser(id);
		/*Set<Question> ques = new HashSet<Question>(user.getQuestion());
		for(Question q: ques) {
			System.out.println(q.getQuestionTitle());
			System.out.println("-----------------------------------------------------------");
		}*/
		model.addAttribute("usr",user);
		return "viewProfile";
	}

	
	@RequestMapping(value = "/answers")
	public String getAnswers(Principal principal, Model model) {
		Usr user = usrService.getUserbyEmail(principal.getName());
		List<Question> quest = usrService.getQuestionsAnswered(user);//questions that have been answered by the user 
		model.addAttribute("usr", user);
		model.addAttribute("question", quest);
		return "viewProfileAns";
		
	}
	
	@RequestMapping(value = "/answers/{id}")
	public String getUserAnswers(Model model, @PathVariable long id) {
		Usr user = usrService.getUser(id);
		List<Question> quest = usrService.getQuestionsAnswered(user);//questions that have been answered by the user 
		model.addAttribute("usr", user);
		model.addAttribute("question", quest);
		return "viewProfileAns";
		
	}
	
	@RequestMapping(value = "/profilePic/{id}", method=RequestMethod.GET)
	public @ResponseBody String getProfilePic(HttpServletResponse response, @PathVariable long id) throws IOException {
		Usr user = usrService.getUser(id);
		byte[] photoBytes = user.getPhotoBytes();
		if(photoBytes !=null) {
			int photoLength = photoBytes.length;
			try(ServletOutputStream sos = response.getOutputStream()){
				
			
				response.setContentType(user.getPhotoContentType());
				response.setContentLength(photoLength);
				response.setHeader("Content-Disposition", "inline; filename=\""+user.getFirstName()+"_"+user.getLastName()+"\"");
			
				sos.write(photoBytes);
				sos.flush();
				
			}
		}
		return null;
		
	}

}
