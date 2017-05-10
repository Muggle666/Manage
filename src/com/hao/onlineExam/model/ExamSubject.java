package com.hao.onlineExam.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exam_subject")
public class ExamSubject implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	@Column(name = "sub_id", nullable = false)
//	@NotNull(message = "不可以为空")
	private Integer subId;
	
	@Column(name = "name", length = 45, nullable = false)
//	@NotEmpty(message = "不可以为空")
	private String name;
	
	@Column(name = "description", length = 255, nullable = false)
	private String description;
	
	@Column(name = "test_count", nullable = false)
	private Integer testCount;
	
	@Column(name = "total_time", nullable = false)
	private Integer totalTime;
	
	@Column(name = "total_score", nullable = false)
	private double totalScore;
	
	@Column(name = "status", nullable = false)
	private Integer status;
	
	@OneToMany(mappedBy = "subject")
	private Set<ExamUserSubject> examUserSubject;
	
	
	public ExamSubject() {
		super();
	}

	
	
	public ExamSubject(Integer subId, String name, String description, Integer testCount, Integer totalTime,
			double totalScore, Integer status, Set<ExamUserSubject> examUserSubject) {
		super();
		this.subId = subId;
		this.name = name;
		this.description = description;
		this.testCount = testCount;
		this.totalTime = totalTime;
		this.totalScore = totalScore;
		this.status = status;
		this.examUserSubject = examUserSubject;
	}



	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getTestCount() {
		return testCount;
	}
	public void setTestCount(Integer testCount) {
		this.testCount = testCount;
	}
	public Integer getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<ExamUserSubject> getExamUserSubject() {
		return examUserSubject;
	}

	public void setExamUserSubject(Set<ExamUserSubject> examUserSubject) {
		this.examUserSubject = examUserSubject;
	}
}
