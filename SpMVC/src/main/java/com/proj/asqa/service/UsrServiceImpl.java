package com.proj.asqa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proj.asqa.dao.UsrDAO;
import com.proj.asqa.domain.Answer;
import com.proj.asqa.domain.Question;
import com.proj.asqa.domain.Usr;

@Service
public class UsrServiceImpl implements UsrService {
	@Autowired
	//@Qualifier("usrHibernateImplDAO")
	private UsrDAO usrDAO;
	
	@Autowired
	private AnswerService ansService;

	@Override
	@Transactional
	public void addUsr(Usr usr) {
		//usr.getRole().add(e)
		usrDAO.addUsr(usr);

	}

	@Override
	@Transactional
	public Collection<Usr> listUsr() {
		// TODO Auto-generated method stub
		return usrDAO.listUsr();
	}

	@Override
	@Transactional
	public Usr getUser(Long id) {
		
		return usrDAO.getUser(id);
	}

	@Override
	@Transactional
	public void saveorUpdate(Usr usr) {
		usrDAO.saveorUpdate(usr);
		
	}

	@Override
	@Transactional
	public Usr getUserbyEmail(String email) {
		
		return usrDAO.getUserbyEmail(email);
		
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		usrDAO.deleteUser(id);
		
	}

	@Override
	@Transactional
	public List<Question> getQuestionsAnswered(Usr user) {
		List<Answer> answer = ansService.getAnswerbyUser(user.getId());
		Set<Question> questSet = new HashSet<Question>(); 
		for(Answer ans :answer) {
			questSet.add(ans.getQuestion());
		}
		
		List<Question> questList = new ArrayList<Question>(questSet);
		
		return questList;
	}

}
