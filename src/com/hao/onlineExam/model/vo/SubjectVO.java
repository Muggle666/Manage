package com.hao.onlineExam.model.vo;

public class SubjectVO {

	private Integer subId;
	private String name;
	private String description;
	private Integer testCount;
	private Integer totalTime;
	private double totalScore;
	private Integer status;
	private ExamScoreVO examScoreVO;
	
	public SubjectVO() {
		super();
	}
	public SubjectVO(Integer subId, String name, String description, Integer testCount, Integer totalTime,
			double totalScore, Integer status, ExamScoreVO examScoreVO) {
		super();
		this.subId = subId;
		this.name = name;
		this.description = description;
		this.testCount = testCount;
		this.totalTime = totalTime;
		this.totalScore = totalScore;
		this.status = status;
		this.examScoreVO = examScoreVO;
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
	public ExamScoreVO getExamScoreVO() {
		return examScoreVO;
	}
	public void setExamScoreVO(ExamScoreVO examScoreVO) {
		this.examScoreVO = examScoreVO;
	}
	
}
