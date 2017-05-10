package com.hao.onlineExam.dao.imp;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hao.onlineExam.dao.IUserAndSubjectDAO;
import com.hao.onlineExam.model.ExamScore;
import com.hao.onlineExam.model.ExamUserSubject;

@Repository
public class UserAndSubjectDAOImp extends BaseHibernateDAO<ExamUserSubject> implements IUserAndSubjectDAO{

	@Autowired
	private SessionFactory sessionFactory;//负责初始化hibernate，创建session对象
	
	public Session getSession() {
//		return this.sessionFactory.getCurrentSession();
		return this.sessionFactory.openSession();
	}
	
	@Override
	public List<ExamUserSubject> findSubjectByUser() throws Exception {
		String hql = "FROM ExamUserSubject";
		List<ExamUserSubject> examUserSubject = this.queryForList(hql, null);
		return examUserSubject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ExamUserSubject> getSubjectByUserId(String userId) {
		String hql = "FROM ExamUserSubject es WHERE es.userId = :userId";
		return (Collection<ExamUserSubject>) this.createQuery(hql)
				.setString("userId", userId).list();
	}

}
