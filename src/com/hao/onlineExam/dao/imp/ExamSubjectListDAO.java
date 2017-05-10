package com.hao.onlineExam.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hao.onlineExam.dao.IExamSubjectListDAO;
import com.hao.onlineExam.model.ExamSubject;

@Repository
public class ExamSubjectListDAO extends BaseHibernateDAO<ExamSubject> implements IExamSubjectListDAO{

	@Override
	public List<ExamSubject> findAllSubject() {
		return queryForList("FROM ExamSubject", null);
	}

}
