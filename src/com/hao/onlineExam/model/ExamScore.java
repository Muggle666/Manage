package com.hao.onlineExam.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exam_score")
public class ExamScore {
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "user_id", length = 45, nullable = false)
	private String userId;
	
	@Column(name = "subject_id", length = 45, nullable = false)
	private Integer subId;
	
	@Column(name = "exam_time", length = 45, nullable = false)
	private Date examTime;
	
	@Column(name = "score", length = 45)
	private double score;
	
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
//	@JoinColumn(name = "sub_id", referencedColumnName = "sub_id" )
//	private ExamSubject subject;
//	
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
//	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
//	private User user;

	public ExamScore() {
		super();
	}


	public ExamScore(Integer id, String userId, Integer subId, Date examTime, double score) {
		super();
		this.id = id;
		this.userId = userId;
		this.subId = subId;
		this.examTime = examTime;
		this.score = score;
	}
	
//	public ExamScore(Integer id, String userId, Integer subId, Date examTime, double score, ExamSubject subject,
//			User user) {
//		super();
//		this.id = id;
//		this.userId = userId;
//		this.subId = subId;
//		this.examTime = examTime;
//		this.score = score;
//		this.subject = subject;
//		this.user = user;
//	}


	public Integer getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public Date getExamTime() {
		return examTime;
	}

	public void setExamTime(Date examTime) {
		this.examTime = examTime;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

//	public ExamSubject getSubject() {
//		return subject;
//	}
//
//	public void setSubject(ExamSubject subject) {
//		this.subject = subject;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

}
