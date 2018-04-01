package com.proj.asqa.dao;

import java.util.List;

import com.proj.asqa.domain.Question;

public interface QuestionDAO {
	public List<Question> getAllQuestions();
	public Question getQuestionbyID(Long id);
	public void saveOrUpdate(Question question);
	public void deleteQuestion(Long id);
	public List<Question> getFlaggedQuestion();
	
}
