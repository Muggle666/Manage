package com.hao.onlineExam.model.vo;

import java.util.Date;

public class ExamScoreVO {
	
	private Integer id;
	private String userId;
	private Integer subId;
	private Date examTime;
	private double score;
	private long remainingExamTime;
	private String examTimeString;
	private String userName;
	private String name;
	private Date deadline;

	public ExamScoreVO(Integer id, String userId, Integer subId, Date examTime, double score, long remainingExamTime,
			String examTimeString, String userName, String name, Date deadline) {
		super();
		this.id = id;
		this.userId = userId;
		this.subId = subId;
		this.examTime = examTime;
		this.score = score;
		this.remainingExamTime = remainingExamTime;
		this.examTimeString = examTimeString;
		this.userName = userName;
		this.name = name;
		this.deadline = deadline;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public ExamScoreVO() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExamTimeString() {
		return examTimeString;
	}

	public void setExamTimeString(String examTimeString) {
		this.examTimeString = examTimeString;
	}

	public long getRemainingExamTime() {
		return remainingExamTime;
	}

	public void setRemainingExamTime(long remainingExamTime) {
		this.remainingExamTime = remainingExamTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
