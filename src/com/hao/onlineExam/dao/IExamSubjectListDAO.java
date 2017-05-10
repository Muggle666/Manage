package com.hao.onlineExam.dao;

import java.util.List;

import com.hao.onlineExam.model.ExamSubject;

public interface IExamSubjectListDAO extends IBaseDAO<ExamSubject>{
	public List<ExamSubject> findAllSubject();
}
