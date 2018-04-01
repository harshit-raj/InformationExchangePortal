package com.proj.asqa.dao;

import java.util.List;

import com.proj.asqa.domain.Answer;

public interface AnswerDAO {
	public Answer getAnswer(long id);
	public List<Answer> getAnswerbyUser(long id);
	public List<Answer> getAnswerbyQues(long id);

}
