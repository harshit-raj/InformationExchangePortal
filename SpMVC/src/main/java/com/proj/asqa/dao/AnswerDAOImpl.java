package com.proj.asqa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.asqa.domain.Answer;
import com.proj.asqa.domain.Usr;
@Repository
public class AnswerDAOImpl implements AnswerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public Answer getAnswer(long id) {
		return (Answer) sessionFactory.getCurrentSession().get(Answer.class, id);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getAnswerbyUser(long id) {
		List<Answer> ansList = new ArrayList<Answer>();
		String querry = "from Answer where userID=?";
		ansList = sessionFactory.getCurrentSession().createQuery(querry).setParameter(0, id).list();
		
		return ansList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getAnswerbyQues(long id) {
		List<Answer> ansList = new ArrayList<Answer>();
		String querry = "from Answer where questionID=?";
		ansList = sessionFactory.getCurrentSession().createQuery(querry).setParameter(0, id).list();
		System.out.println(ansList.size());
		System.out.println("-------------------------------------------------------------------");
		return ansList;
	}

}
