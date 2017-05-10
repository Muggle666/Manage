package com.hao.onlineExam.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hao.onlineExam.model.vo.ExamScoreVO;

@Transactional
public interface IExamScoreService {
	
	public List<ExamScoreVO> getAllExamScore();
	
	public double getExamScore(String userId,Integer subId);
}
