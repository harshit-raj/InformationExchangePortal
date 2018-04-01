package com.proj.asqa.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "ROLE")
public class UserRole {
	@Id
	@GeneratedValue
	private long userRoleId;
	private String role;
	@ManyToOne
	@JoinColumn(name = "userId")
	//@JoinColumn(name = "userId")
	private Usr user;
	//private List<Usr> user = new ArrayList<Usr>();

	public Usr getUser() {
		return user;
	}
	public void setUser(Usr user) {
		this.user = user;
	}
	
	
	public long getUserRoleId() {
		return userRoleId;
	}
	
	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	/*public List<Usr> getUser() {
		return user;
	}
	public void setUser(List<Usr> user) {
		this.user = user;
	}
	public void addUser(Usr usr) {
		user.add(usr);
	}
	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", role=" + role + ", user=" + user + "]";
	}
	*/
	

}
