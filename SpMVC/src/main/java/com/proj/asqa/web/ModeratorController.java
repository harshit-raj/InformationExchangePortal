package com.proj.asqa.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.asqa.domain.Question;
import com.proj.asqa.domain.Usr;
import com.proj.asqa.service.QuestionService;
import com.proj.asqa.service.UsrService;

@Controller
@RequestMapping("/moderate")
public class ModeratorController {
	@Autowired
	QuestionService questService;
	@Autowired
	UsrService usrService; 
	@RequestMapping()
	public String getFlaggedQuest(Model model, Principal principal) {
		Usr user = usrService.getUserbyEmail(principal.getName());
		List<Question> flagList = questService.getFlaggedQuestion();
		model.addAttribute("usr", user);
		model.addAttribute("ques", flagList);
		return "flagedQues";
	}
	
	@RequestMapping("/unflag/{id}")
	public String unFlagQuest(Model model, Principal principal,@PathVariable long id) {
		Question quest = questService.getQuestionbyID(id);
		quest.setFlag(false);
		questService.saveOrUpdate(quest);
		return "redirect:/moderate";
	}

}
