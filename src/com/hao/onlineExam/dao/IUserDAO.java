package com.hao.onlineExam.dao;

import java.util.List;

import com.hao.onlineExam.model.User;

public interface IUserDAO extends IBaseDAO<User>{
	
	public List<User> findAllUsers();
	
	public void createUser(User user)throws Exception;
	
	public void editUser(User user)throws Exception;
	
	public User getUserById(String userId) throws Exception;

	public void deleteUser(String userId) throws Exception;
	
}

	

	
