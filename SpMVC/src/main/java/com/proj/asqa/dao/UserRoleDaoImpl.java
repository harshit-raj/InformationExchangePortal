package com.proj.asqa.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.proj.asqa.domain.UserRole;
@Repository
public class UserRoleDaoImpl implements UserRoleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addRole(UserRole role) {
		sessionFactory.getCurrentSession().save(role);

	}

	@Override
	public Collection<UserRole> getAllRole() {
		return sessionFactory.getCurrentSession().createQuery("from UserRole").list();
	}

	@Override
	public UserRole getRole(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveorUpdate(UserRole role) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRole(Long id) {
		// TODO Auto-generated method stub

	}

}
