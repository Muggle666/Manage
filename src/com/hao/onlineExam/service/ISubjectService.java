package com.hao.onlineExam.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.vo.ExamUserVO;
import com.hao.onlineExam.model.vo.SubjectVO;

@Transactional
public interface ISubjectService {
	
	public List<SubjectVO> findAllSubjects();
	
	public List<SubjectVO> findSubjectsBySubId(Integer subId);
	
	public void createSubject(SubjectVO subjectVO) throws Exception;
	
	public void editSubject(SubjectVO subjectVO) throws Exception;
	
	public ExamSubject getSubjectById(Integer userId) throws Exception;

	public void deleteSubject(Integer subId) throws Exception;
	
	public Map<String,Object> getAllStudent(Integer subId);
	
	public List<String> getAllUserBySubject(Integer subId);
	
	public List<ExamUserVO> getAllStudent();
	
	public void saveSubjectStudent(List<String> subjectStudents,Integer subId) throws RuntimeException;

	public void getStatusBySubject(Integer status,Integer subId);
	
	public SubjectVO getSubjectVOById(String userId, Integer subId) ;
}
