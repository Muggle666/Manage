package com.hao.onlineExam.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.onlineExam.dao.IExamScoreDAO;
import com.hao.onlineExam.model.ExamScore;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.service.IExamScoreService;

@Service
public class ExamScoreServiceImp implements IExamScoreService{

	@Autowired
	private IExamScoreDAO examScoreDAO;
	
	@Override
	public List<ExamScoreVO> getAllExamScore() {
		List<ExamScore> examScore = examScoreDAO.loadAll();
		List<ExamScoreVO> examScoreVO = new ArrayList<ExamScoreVO>();
		for(ExamScore score : examScore){
			ExamScoreVO vo = new ExamScoreVO();
			BeanUtils.copyProperties(score, vo);
			examScoreVO.add(vo);
		}
		return examScoreVO;
	}

	@Override
	public double getExamScore(String userId,Integer subId) {
		return 0;
	}

}
