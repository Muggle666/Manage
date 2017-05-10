package com.hao.onlineExam.model.vo;

import javax.validation.constraints.NotNull;

public class ExamTestVO {

	private Integer id;
	@NotNull(message = "不可以为空")
	private String content;
	private String chooseA;
	private String chooseB;
	private String chooseC;
	private String chooseD;
	private Integer subId;
	private String name;
	private String answer;
	private Integer testTypeId;
	private ExamTestTypeVO examTestTypeVO;
	
	
	public Integer getTestTypeId() {
		return testTypeId;
	}
	public void setTestTypeId(Integer testTypeId) {
		this.testTypeId = testTypeId;
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
	public String getChooseC() {
		return chooseC;
	}
	public String getChooseB() {
		return chooseB;
	}
	public void setChooseB(String chooseB) {
		this.chooseB = chooseB;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public ExamTestTypeVO getExamTestTypeVO() {
		return examTestTypeVO;
	}
	public void setExamTestTypeVO(ExamTestTypeVO examTestTypeVO) {
		this.examTestTypeVO = examTestTypeVO;
	}

}
