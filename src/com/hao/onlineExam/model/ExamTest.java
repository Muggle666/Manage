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
@Table(name = "exam_test")
public class ExamTest implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id", length = 11, nullable = false)
	private Integer id;

	@Column(name = "content", length = 1000, nullable = false)
	private String content;

	@Column(name = "choose_a", length = 255, nullable = false)
	private String chooseA;

	@Column(name = "choose_b", length = 255, nullable = false)
	private String chooseB;

	@Column(name = "choose_c", length = 255, nullable = false)
	private String chooseC;

	@Column(name = "choose_d", length = 255, nullable = false)
	private String chooseD;

	@Column(name = "sub_id", length = 11, nullable = false)
	private Integer subId;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "test_type_id")//指定产生的外键字段名
	private ExamTestType examTestType;

	@Column(name = "answer", length = 10, nullable = false)
	private String answer;

	public ExamTest() {
		super();
	}

	

	public ExamTest(Integer id, String content, String chooseA, String chooseB, String chooseC, String chooseD,
			Integer subId, ExamTestType examTestType, String answer) {
		super();
		this.id = id;
		this.content = content;
		this.chooseA = chooseA;
		this.chooseB = chooseB;
		this.chooseC = chooseC;
		this.chooseD = chooseD;
		this.subId = subId;
		this.examTestType = examTestType;
		this.answer = answer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChooseA() {
		return chooseA;
	}

	public void setChooseA(String chooseA) {
		this.chooseA = chooseA;
	}

	public String getChooseB() {
		return chooseB;
	}

	public void setChooseB(String chooseB) {
		this.chooseB = chooseB;
	}

	public String getChooseC() {
		return chooseC;
	}

	public void setChooseC(String chooseC) {
		this.chooseC = chooseC;
	}

	public String getChooseD() {
		return chooseD;
	}

	public void setChooseD(String chooseD) {
		this.chooseD = chooseD;
	}

	public Integer getSubId() {
		return subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public ExamTestType getExamTestType() {
		return examTestType;
	}

	public void setExamTestType(ExamTestType examTestType) {
		this.examTestType = examTestType;
	}

}
