package com.proj.asqa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.asqa.domain.Question;
import com.proj.asqa.domain.Usr;
@Repository
public class QuestionDAOImpl implements QuestionDAO {
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public List<Question> getAllQuestions() {
		return sessionFactory.getCurrentSession().createQuery("from Question").list();
		
	}

	@Override
	public Question getQuestionbyID(Long id) {
		
		List<Question> quesList = new ArrayList<Question>();
		String querry = "from Question where questionId=?";
		
		quesList = sessionFactory.getCurrentSession().createQuery(querry).setParameter(0, id).list();
		//System.out.println("Got userlist "+ userList.get(0));
		if(quesList.size() >0) {
			//System.out.println("returning------------------");
			return quesList.get(0);
		}
		else {
			return null;
		}
		
		
		
		//return (Question) sessionFactory.getCurrentSession().get(Question.class, id);
	}

	@Override
	public void saveOrUpdate(Question question) {
		sessionFactory.getCurrentSession().saveOrUpdate(question);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteQuestion(Long id) {
		sessionFactory.getCurrentSession().delete(getQuestionbyID(id));
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public List<Question> getFlaggedQuestion() {
		List<Question> quesList = new ArrayList<Question>();
		String querry = "from Question where flag=?";
		
		quesList = sessionFactory.getCurrentSession().createQuery(querry).setParameter(0, true).list();
		
		return quesList;
	}

	

}
