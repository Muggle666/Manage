package com.hao.onlineExam.dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hao.onlineExam.dao.IUserDAO;
import com.hao.onlineExam.model.User;

@Repository
@Transactional
public class UserHibernateDAOImp extends BaseHibernateDAO<User> implements IUserDAO{

	@Autowired
	private SessionFactory sessionFactory;//负责初始化hibernate，创建session对象
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
//		return this.sessionFactory.openSession();
	}
	@Override
	public List<User> findAllUsers() {
		return queryForList("FROM User",null);
	}
	@Override
	public void createUser(User user) throws Exception {
		save(user);
	}
	@Override
	public void editUser(User user) throws Exception {
		update(user);
	}
	@Override
	public User getUserById(String userId) throws Exception {
		return get(userId);
	}
	@Override
	public void deleteUser(String userId) throws Exception {
//		this.load(userId);
		this.delete(this.get(userId));
	}
}
