package com.hao.onlineExam.dao;

import java.util.List;

import com.hao.onlineExam.model.ExamTest;
import com.hao.onlineExam.model.PagerModel;

public interface IExamTestDAO extends IBaseDAO<ExamTest>{
	
	public PagerModel<ExamTest> findAllExamTest();
	
	public void createExamTest(ExamTest examTest) throws Exception;
	
	public void editExamTest(ExamTest examTest) throws Exception;
	
	public void delateExamTest(Integer id) throws Exception;
	
	public ExamTest getExamTestById(Integer id);

	public List<ExamTest> getTestListBySubAndTestType(Integer subId, Integer id);

	public List<ExamTest> getTestListByIdList(List<Integer> randomTestIdList);
	
	public List<ExamTest> getExamTestBySubId(Integer subId);
}
