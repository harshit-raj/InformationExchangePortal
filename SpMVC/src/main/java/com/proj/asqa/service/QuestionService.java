package com.proj.asqa.service;

import java.util.List;

import com.proj.asqa.domain.Question;
import com.proj.asqa.domain.Usr;

public interface QuestionService {
	public List<Question> getAllQuestions();
	public Question getQuestionbyID(Long id);
	public void saveOrUpdate(Question question);
	public void deleteQuestion(Long id);
	public void removeQuestion(Long id);
	public boolean flagQuestion(long id);
	public List<Question> getFlaggedQuestion();

}
