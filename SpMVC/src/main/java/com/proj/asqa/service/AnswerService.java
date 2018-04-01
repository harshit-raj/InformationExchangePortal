package com.proj.asqa.service;

import java.util.List;

import com.proj.asqa.domain.Answer;

public interface AnswerService {
	public Answer getAnswer(long id);
	public List<Answer> getAnswerbyUser(long id);
	public List<Answer> getAnswerbyQues(long id);
}
