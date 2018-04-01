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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
//import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Question {
	@Id
	@GeneratedValue
	private Long questionId;
	//private static AtomicLong idSequence = new AtomicLong();
	@NotEmpty
	private String questionTitle;
	private String questionDesc;
	private String tags; 
	@ManyToOne
	@JoinColumn(name = "userId")
	private Usr user;
	private boolean flag = false;
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "question",fetch = FetchType.EAGER,orphanRemoval=true)
	//@OneToMany(mappedBy = "question",fetch = FetchType.EAGER)
	//@Cascade({CascadeType.SAVE_UPDATE})
	
	private List<Answer> answers= new ArrayList<Answer>();
	
	
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public Usr getUser() {
		return user;
	}
	public void setUser(Usr user) {
		this.user = user;
	}
	
	
	
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionTitle=" + questionTitle + ", questionDesc="
				+ questionDesc + ", tags=" + tags + ",  user=" + user + ", flag=" + flag + "]";
	}
	
	
	
	
	
}
