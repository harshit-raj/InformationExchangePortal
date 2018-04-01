package com.proj.asqa.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

//import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
@Entity
@Table(name = "USER")
public class Usr {
	@Id
	@GeneratedValue
	private Long userId;
	private static AtomicLong idSequence = new AtomicLong();
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@Column(unique  = true)
	private String email;
	private String phoneNumber;
	private String type;
	private String password;
	@Transient
	private MultipartFile photo;
	@Lob 
	private byte[] photoBytes;
	private String photoContentType;
	@Transient
	private String passwordConfirm;
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "user",fetch = FetchType.EAGER)//fetch = FetchType.EAGER
	//@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	//@Cascade({CascadeType.SAVE_UPDATE})
	private List<Question> question = new ArrayList<Question>();
	//@OneToMany(cascade = {CascadeType.ALL},mappedBy = "user",fetch = FetchType.EAGER)
	//@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "userRoleId"))
	
	//@ManyToMany(cascade = {CascadeType.ALL})
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "user",fetch = FetchType.EAGER)
	//@Cascade({CascadeType.SAVE_UPDATE})
	private List<UserRole> roleList = new ArrayList<UserRole>();
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "user",fetch = FetchType.EAGER)
	//@OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
	//@Cascade({CascadeType.ALL})
	private List<Answer> answer = new ArrayList<Answer>();
	
	
	
	
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
		setPhotoContentType(photo.getContentType());
		try {
			setPhotoBytes(photo.getBytes());
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public byte[] getPhotoBytes() {
		return photoBytes;
	}
	public void setPhotoBytes(byte[] photoBytes) {
		this.photoBytes = photoBytes;
	}
	
	public String getPhotoContentType() {
		return photoContentType;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	public List<UserRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<UserRole> roleList) {
		this.roleList = roleList;
	}
	public Long getId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public List<UserRole> getRole() {
		return roleList;
	}
	public void setRole(List<UserRole> role) {
		this.roleList = role;
	}
	public void setId(Long id) {
		this.userId = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public List<Question> getQuestion() {
		return question;
	}
	public void setQuestion(List<Question> question) {
		this.question = question;
	}
	
	public List<Answer> getAnswer() {
		return answer;
	}
	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}
	
	public Long assignId() {
		this.userId = idSequence.incrementAndGet();
		return userId;
	}
	@Override
	public String toString() {
		return "Usr [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", type=" + type + ", question=" + question + "]";
	}
	
	
	

}
