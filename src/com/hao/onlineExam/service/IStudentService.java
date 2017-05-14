package com.hao.onlineExam.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.User;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.model.vo.ExamTestVO;
import com.hao.onlineExam.model.vo.SubjectVO;

@Transactional
public interface IStudentService {
	
	/*-----------------------------考试科目--------------------------------------------------*/
	public List<SubjectVO> findAllSubject(User userVO) throws Exception;

	public ExamScoreVO getScoreById(Integer scoreId);
	
	/*-----------------------------考试--------------------------------------------------*/

	Map<Integer, List<ExamTestVO>> getTestListBySubject(Integer subId, String userId);

	public ExamScoreVO calculateScore(List<Integer> answerIds, Map<String, String[]> testMap, String userId, Integer subId);

	public ExamSubject getSubjectById(Integer subId);
	
	public SubjectVO getSubjectVOById(String userId, Integer subId) ;
}
