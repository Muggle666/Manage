package com.hao.onlineExam.dao;

import java.util.List;
import java.util.Map;

import com.hao.onlineExam.model.ExamSubject;

public interface ISubjectDAO extends IBaseDAO<ExamSubject>{
	
	public List<ExamSubject> findAllSubjects();
	
	public ExamSubject findSubjectsBySubId(Integer subId);
	
	public void createSubject(ExamSubject subject)throws Exception;
	
	public void editSubject(ExamSubject subject)throws Exception;
	
	public ExamSubject getSubjectById(Integer subId);

	public void deleteSubject(Integer subId) throws Exception;
	
	public List<String> getAllUserBySubject(Integer subId);
	
	public Map<String,Object> getAllStudent(Integer subId);

	public void insertSubjectUsers(List<String> insertList, Integer subId);

	public void deleteSubjectUsers(List<String> delList, Integer subId);
	
	public void getStatusBySubject(Integer status,Integer subId);
}
