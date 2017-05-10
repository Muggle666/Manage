package com.hao.onlineExam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hao.onlineExam.model.ExamUserSubject;

@Transactional
public interface IUserAndSubjectService {
	public List<ExamUserSubject> findSubjectByUser() throws Exception;
}
