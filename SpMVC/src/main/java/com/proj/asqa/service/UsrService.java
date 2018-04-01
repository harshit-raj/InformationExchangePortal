package com.proj.asqa.service;

import java.util.Collection;
import java.util.List;

import com.proj.asqa.domain.Question;
import com.proj.asqa.domain.Usr;

public interface UsrService {
	public void addUsr(Usr usr);
	public Collection<Usr> listUsr();
	public Usr getUser(Long id);
	public void saveorUpdate(Usr usr);
	public Usr getUserbyEmail(String email);
	public void deleteUser(Long id);
	public List<Question> getQuestionsAnswered(Usr user);
}
