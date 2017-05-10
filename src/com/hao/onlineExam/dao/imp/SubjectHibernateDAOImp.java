package com.hao.onlineExam.dao.imp;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hao.onlineExam.dao.ISubjectDAO;
import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.vo.SubjectVO;

@Repository
@Transactional
public class SubjectHibernateDAOImp extends BaseHibernateDAO<ExamSubject> implements ISubjectDAO{
	
	@Autowired
	private SessionFactory sessionFactory;//负责初始化hibernate，创建session对象

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
//		return this.sessionFactory.openSession();
	}
	public SubjectHibernateDAOImp() {
	}
//	public ExamSubject getSubjectById(String subId){
//		return get(subId);
//}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllUserBySubject(Integer subId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select user.user_id as userId");
		sql.append(" from exam_user_subject eus");
		sql.append(" left join exam_subject es on eus.sub_id = es.sub_id");
		sql.append(" left join exam_user user on user.user_id = eus.user_id");
		sql.append(" where es.sub_id = :subId");
		List<String> list = this.createSqlQuery(String.valueOf(sql)).addScalar("userId", StringType.INSTANCE).setInteger("subId", subId).list();
		return list;
	}
	
	public List<ExamSubject> findAllSubjects(){
		return queryForList("FROM ExamSubject", null);
	}
	public void createSubject(ExamSubject subject) throws Exception{
		save(subject);
	}
	public void editSubject(ExamSubject subject) throws Exception{
		update(subject);
	}
	public void deleteSubject(Integer subId) throws Exception{
		delete(this.get(subId));
	}
	@Override
	public ExamSubject getSubjectById(Integer subId){
		return get(subId);
	}
	@Override
	public Map<String, Object> getAllStudent(Integer subId) {
		return null;
	}
	@Override
	public void insertSubjectUsers(List<String> insertList, Integer subId) {
		String sql = null;
		for(String userId : insertList){
			sql = "insert into exam_user_subject(user_id , sub_id) values(:userId, :subId)";
			this.createSqlQuery(sql).setString("userId", userId).setInteger("subId", subId).executeUpdate();
		}
	}
	@Override
	public void deleteSubjectUsers(List<String> delList, Integer subId) {
		String sql = null;
		for(String userId : delList){
			sql = "delete exam_user_subject from exam_user_subject where user_id = :userId and sub_id = :subId";
			this.createSqlQuery(sql).setString("userId", userId).setInteger("subId", subId).executeUpdate();
		}
	}
	@Override
	public void getStatusBySubject(Integer status,Integer subId) {
		String sql = null;
		sql = "update exam_subject set status = :status where sub_id = :subId";
		this.createSqlQuery(sql).setInteger("status", status).setInteger("subId", subId).executeUpdate();
	}

	@Override
	public ExamSubject findSubjectsBySubId(Integer subId) {
		ExamSubject examSubject = this.get(subId);
		return examSubject;
	}
	
}
