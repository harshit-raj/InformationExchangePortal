package com.proj.asqa.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proj.asqa.dao.UsrDAO;
import com.proj.asqa.dao.UsrDAOImpl;
import com.proj.asqa.domain.UserRole;
import com.proj.asqa.domain.Usr;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UsrDAO usrDAO;
	
	
	
	/*@Autowired
	@Qualifier("usrHibernateImplDAO")
	private UsrDAO userDAO;
	@Override
	@Transactional(readOnly = true)*/
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//userDAO = new UsrHibernateImplDAO();
		//System.out.println(userDAO.getUser((long) 1));
		//System.out.println("Start auth" + email);
		//Usr user = userDAO.getUserbyEmail(email);
	
		/*
		System.out.println("In iser detail service");
		if(user !=null) {
			Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
			for(UserRole role :user.getRole()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
				return new User(user.getEmail(),user.getPassword(),grantedAuthorities);
			}
		}
		else {
			System.out.println("Error!!!!!!!!!");
			throw new UsernameNotFoundException("User not found");
		}
		return null;
		*
		*/
		System.out.println("Auth started---------------------------------------------------");
		Usr user = usrDAO.getUserbyEmail(email);
		if(user != null) {
			
			System.out.println("User present---------------------------------------------------------");
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

			for (UserRole role : user.getRole()){
	            authorities.add(new SimpleGrantedAuthority(role.getRole()));
	        }
			return new User(email, user.getPassword(), authorities);
		}
		/*if(email.equals("raj.0489@gmail.com")) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

			for (UserRole role : user.getRole()){
	            authorities.add(new SimpleGrantedAuthority(role.getRole()));
	        }
			//authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			//authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new User(email, user.getPassword(), authorities);
		}
		else if(email.equals("raj@gmail.com")) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new User(email, "6789", authorities);
		}*/
		throw new UsernameNotFoundException("User / Passqword not valid");
	}

}
