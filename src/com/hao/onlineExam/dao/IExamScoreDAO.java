package com.hao.onlineExam.dao;

import java.util.List;
import java.util.Map;

import com.hao.onlineExam.model.ExamScore;
import com.hao.onlineExam.model.vo.ExamScoreVO;

public interface IExamScoreDAO extends IBaseDAO<ExamScore>{
	
	public List<ExamScoreVO> getAllExamScore(Map<String,String> conditionMap);
	
	public ExamScore getExamScore(String userId,Integer subId);

	public ExamScoreVO getScoreById(Integer scoreId);
	
//	public ExamScore getScoreBySubId(Integer subId);
}
