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
@Table(name = "exam_test_type")
public class ExamTestType implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id", length = 11, nullable = false)
	private Integer id;

	@Column(name = "test_type", length = 45, nullable = false)
	private String testType;

	@Column(name = "test_type_score", length = 11, nullable = false)
	private Integer testTypeScore;
	
	@OneToMany(mappedBy = "examTestType")
	private Set<ExamTest> examTest;

//	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE })
//	@JoinColumn(name = "sub_id")//指定产生的外键字段名
//	private ExamSubject examSubject;
	
	public ExamTestType() {
		super();
	}

	public ExamTestType(Integer id, String testType, Integer testTypeScore) {
		this.id = id;
		this.testType = testType;
		this.testTypeScore = testTypeScore;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public Integer getTestTypeScore() {
		return testTypeScore;
	}

	public void setTestTypeScore(Integer testTypeScore) {
		this.testTypeScore = testTypeScore;
	}

	public Set<ExamTest> getExamTest() {
		return examTest;
	}

	public void setExamTest(Set<ExamTest> examTest) {
		this.examTest = examTest;
	}

}
