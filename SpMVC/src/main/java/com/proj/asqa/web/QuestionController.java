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
import org.springframework.web.bind.annotation.ResponseBody;

import com.proj.asqa.domain.Answer;
import com.proj.asqa.domain.Question;
import com.proj.asqa.domain.Usr;
import com.proj.asqa.service.AnswerService;
import com.proj.asqa.service.QuestionService;
import com.proj.asqa.service.UsrService;

import java.util.List;



@Controller

@RequestMapping("/ques")
public class QuestionController {
	@Autowired
	private UsrService usrService;
	
	@Autowired
	private QuestionService questService;
	
	@Autowired
	private AnswerService ansService;
	
	   
	@RequestMapping(value = "addQuest",method = RequestMethod.POST)
	public String addQuestion(@Valid Question question,BindingResult bindingResult,HttpSession session, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "addQuestion";
		}
		Usr user = usrService.getUserbyEmail(principal.getName());
		user.getQuestion().add(question);
		question.setUser(user);
		//usrService.addUsr(user1);
		usrService.saveorUpdate(user);
		String redirect = "redirect:/ques/viewQuest/" + question.getQuestionId();
		return redirect;
	}
	
	@RequestMapping(value = "/addQuest",method = RequestMethod.GET)
	public String addQuestion(Model model, Principal principal) {
		Usr user = usrService.getUserbyEmail(principal.getName());
		Question question = new Question();
		question.setUser(user);
		System.out.println("From Get");
		model.addAttribute(question);
		model.addAttribute(user);
		return "addQuestion";
	}
	
	@RequestMapping(value = "/delQuest/{id}",method = RequestMethod.GET)
	public String deleteQuestion(Principal principal, @PathVariable("id") long id) {
		Usr user = usrService.getUserbyEmail(principal.getName());
		questService.removeQuestion(id);
		return "redirect:/viewProfile";
	}
	
	@RequestMapping(value = "/delQues/{id}",method = RequestMethod.GET)
	public String deleteQuestionMod(Principal principal, @PathVariable("id") long id) {
		Usr user = usrService.getUserbyEmail(principal.getName());
		questService.removeQuestion(id);
		return "redirect:/moderate";
	}
	
	
	
	@RequestMapping(value = "/viewQuest/{id}",method = RequestMethod.GET)
	public String viewQuestion(Model model, Principal principal, @PathVariable("id") long id,HttpSession session) {
		Usr user = usrService.getUserbyEmail(principal.getName());
		Question quest = questService.getQuestionbyID(id);
		Answer answer = new Answer();
		model.addAttribute("usr",user);
		model.addAttribute("question",quest);
		model.addAttribute("answer",answer);
		session.setAttribute("q", quest);
		return "viewQuestion";
	}
	
	@RequestMapping(value = "/viewQuest",method = RequestMethod.POST)
	public String addAnswer(Model model,@Valid Answer ans,BindingResult bindingResult,Principal principal,HttpSession session) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error thingy");
			return "viewQuestion";
		}
		
		Question quest = (Question) session.getAttribute("q");
		System.out.println(ans.getAnswerText());
		Usr user = usrService.getUserbyEmail(principal.getName());
		user.getAnswer().add(ans);
		quest.getAnswers().add(ans);
		ans.setUser(user);
		ans.setQuestion(quest);
		System.out.println("about to write");
		usrService.saveorUpdate(user);
		model.addAttribute("usr",user);
		model.addAttribute("question",quest);
		String returnPath = "redirect:/ques/viewQuest/" +quest.getQuestionId();
		
		return returnPath;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String browse(Model model, Principal principal) {
		model.addAttribute("usr",usrService.getUserbyEmail(principal.getName()));
		model.addAttribute("question",questService.getAllQuestions()); 
		return "viewQuest";
		
	}
	
	@RequestMapping(value = "report/{id}", method = RequestMethod.POST)
	public @ResponseBody String flagQuestion(Principal principal,@PathVariable("id") long id) {
		System.out.println("AJAX Hit--------------------------------------");
		boolean flag = questService.flagQuestion(id);
		return " ";
		
	}
	
	

}
