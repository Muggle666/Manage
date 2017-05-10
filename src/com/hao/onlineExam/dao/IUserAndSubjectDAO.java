package com.hao.onlineExam.dao;

import java.util.Collection;
import java.util.List;

import com.hao.onlineExam.model.ExamUserSubject;

public interface IUserAndSubjectDAO extends IBaseDAO<ExamUserSubject>{
	
	public List<ExamUserSubject> findSubjectByUser() throws Exception;

	public Collection<ExamUserSubject> getSubjectByUserId(String userId);
}
