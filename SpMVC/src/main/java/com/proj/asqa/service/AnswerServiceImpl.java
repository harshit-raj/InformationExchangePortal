package com.proj.asqa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.asqa.dao.AnswerDAO;
import com.proj.asqa.domain.Answer;
@Service
public class AnswerServiceImpl implements AnswerService {
	@Autowired
	AnswerDAO answerDAO;
	
	@Override
	public Answer getAnswer(long id) {
		return answerDAO.getAnswer(id);
	
	}

	@Override
	public List<Answer> getAnswerbyUser(long id) {
		return answerDAO.getAnswerbyUser(id);
	}

	@Override
	public List<Answer> getAnswerbyQues(long id) {
		return answerDAO.getAnswerbyQues(id);
	}

}
