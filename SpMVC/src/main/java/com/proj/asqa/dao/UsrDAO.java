package com.proj.asqa.dao;

import java.util.Collection;

import com.proj.asqa.domain.Usr;

public interface UsrDAO {
	public void addUsr(Usr usr);
	public Collection<Usr> listUsr();
	public Usr getUser(Long id);
	public Usr getUserbyEmail(String email);
	public void saveorUpdate(Usr usr);
	public void deleteUser(Long id);
	

}
