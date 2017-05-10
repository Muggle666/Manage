package com.hao.onlineExam.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.User;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.model.vo.ExamUserVO;
import com.hao.onlineExam.model.vo.SubjectVO;

@Transactional
public interface IExamSubjectListService {
	public List<SubjectVO> findAllSubject(User userVO) throws Exception;
	
	public ExamScoreVO getScoreById(Integer scoreId);
}
