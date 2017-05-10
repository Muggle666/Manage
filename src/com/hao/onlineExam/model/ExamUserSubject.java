package com.hao.onlineExam.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "exam_user_subject")
public class ExamUserSubject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "user_id", length = 10, nullable = false)
	private String userId;
	
	@Id
	@GeneratedValue
	@Column(name = "sub_id", nullable = false)
	private Integer subId;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "sub_id", referencedColumnName = "sub_id")
	private ExamSubject subject;

	public ExamUserSubject() {
		super();
	}

	public ExamUserSubject(String userId, Integer subId, User user, ExamSubject subject) {
		super();
		this.userId = userId;
		this.subId = subId;
		this.user = user;
		this.subject = subject;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ExamSubject getSubject() {
		return subject;
	}

	public void setSubject(ExamSubject subject) {
		this.subject = subject;
	}


}
