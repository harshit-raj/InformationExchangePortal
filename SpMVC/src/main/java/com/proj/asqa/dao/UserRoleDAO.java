package com.proj.asqa.dao;

import java.util.Collection;

import com.proj.asqa.domain.UserRole;



public interface UserRoleDAO {
	public void addRole(UserRole role);
	public Collection<UserRole> getAllRole();
	public UserRole getRole(Long id);
	public void saveorUpdate(UserRole role);
	public void deleteRole(Long id);

}
