package com.proj.asqa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.proj.asqa.domain.Usr;

import org.hibernate.SessionFactory;
import org.hibernate.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsrDAOImpl implements UsrDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void addUsr(Usr usr) {
		System.out.println("--------------------------------------------------------------");
		sessionFactory.getCurrentSession().save(usr);
		System.out.println("--------------------------------------------------------------");
		

	}

	@Override
	public Collection<Usr> listUsr() {
		return sessionFactory.getCurrentSession().createQuery("from Usr").list();
		//return null;
	}

	@Override
	public Usr getUser(Long id) {
		/*String query = "from Usr where id= " +id;
		Query q =  sessionFactory.getCurrentSession().createQuery(query);
		List<>*/
		/*Usr u = (Usr) sessionFactory.getCurrentSession().get(Usr.class, id); 
		System.out.println(u.getQuestion().size());
		return u;*/
		
		List<Usr> userList = new ArrayList<Usr>();
		String querry = "from Usr where userId=?";
		
		userList = sessionFactory.getCurrentSession().createQuery(querry).setParameter(0, id).list();
		//System.out.println("Got userlist "+ userList.get(0));
		if(userList.size() >0) {
			//System.out.println("returning------------------");
			return userList.get(0);
		}
		else {
			return null;
		}
	}
	
	

	@Override
	public void saveorUpdate(Usr usr) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(usr);
		sessionFactory.getCurrentSession().flush();
		System.out.println("--------------------------------------------------------------");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	//@Transactional
	public Usr getUserbyEmail(String email) {
		
		List<Usr> userList = new ArrayList<Usr>();
		String querry = "from Usr where email=?";
		
		userList = sessionFactory.getCurrentSession().createQuery(querry).setParameter(0, email).list();
		//System.out.println("Got userlist "+ userList.get(0));
		if(userList.size() >0) {
			System.out.println("returning------------------");
			return userList.get(0);
		}
		else {
			return null;
		}
		
	}
	
	

	@Override
	public void deleteUser(Long id) {
		sessionFactory.getCurrentSession().delete(getUser(id));
		
	}
	
	
	
	

}
