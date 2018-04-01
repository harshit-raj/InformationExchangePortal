package com.proj.asqa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proj.asqa.dao.QuestionDAO;
import com.proj.asqa.domain.Answer;
import com.proj.asqa.domain.Question;
import com.proj.asqa.domain.Usr;
@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	QuestionDAO quesDAO;
	
	@Autowired
	UsrService usrService;
	
	@Autowired
	AnswerService ansService;

	@Override
	
	public List<Question> getAllQuestions() {
		return quesDAO.getAllQuestions();
	}

	@Override
	@Transactional
	public Question getQuestionbyID(Long id) {
		return quesDAO.getQuestionbyID(id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Question question) {
		quesDAO.saveOrUpdate(question);
		
	}

	@Override
	@Transactional
	public void deleteQuestion(Long id) {
		quesDAO.deleteQuestion(id);
		
	}

/*	@Override
	@Transactional
	public void removeQuestion(Long id, Usr user) {
		Question quest = getQuestionbyID(id);   //question to be deleted
		
		List<Answer> ansList = ansService.getAnswerbyQues(id); //list of answers on the question
		List<Usr> allUsrs = (List<Usr>) usrService.listUsr();
		for(Usr us: allUsrs) {
			us.getAnswer().removeAll(ansList);
		}
		
		
		user.getAnswer().removeAll(ansList);
		usrService.saveorUpdate(user);
		quest.getAnswers().removeAll(ansList);
		usrService.saveorUpdate(user);
		user.getQuestion().remove(quest);
	    usrService.saveorUpdate(user);
		deleteQuestion(id);
		usrService.saveorUpdate(user);
		
		
	}*/
	
	@Override
	@Transactional
	public void removeQuestion(Long id) { //, Usr user
		Question quest = getQuestionbyID(id);   //question to be deleted
		
		List<Answer> ansList = ansService.getAnswerbyQues(id); //list of answers on the question
		List<Usr> allUsrs = (List<Usr>) usrService.listUsr();
		for(Usr us: allUsrs) {
			us.getAnswer().removeAll(ansList);

		}
		Usr user = quest.getUser();
		
		//user.getAnswer().removeAll(ansList);
		//usrService.saveorUpdate(user);
		quest.getAnswers().clear();
		//usrService.saveorUpdate(user);
		user.getQuestion().remove(quest);
	    //usrService.saveorUpdate(user);
		deleteQuestion(id);
		usrService.saveorUpdate(user);
		
		
	}

	@Override
	@Transactional
	public boolean flagQuestion(long id) {
		Question quest = quesDAO.getQuestionbyID(id);
		quest.setFlag(true);
		quesDAO.saveOrUpdate(quest);
		return true;
	}

	@Override
	@Transactional
	public List<Question> getFlaggedQuestion() {
		List<Question> flagedList = quesDAO.getFlaggedQuestion();
		return flagedList;
	}
	

}
