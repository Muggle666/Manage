package com.hao.onlineExam.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.onlineExam.dao.IUserAndSubjectDAO;
import com.hao.onlineExam.model.ExamUserSubject;
import com.hao.onlineExam.service.IUserAndSubjectService;

@Service
public class UserAndSubjectServiceImp implements IUserAndSubjectService{

	@Autowired
	private IUserAndSubjectDAO user;
	
	@Override
	public List<ExamUserSubject> findSubjectByUser() throws Exception {
		return user.findSubjectByUser();
	}

}
