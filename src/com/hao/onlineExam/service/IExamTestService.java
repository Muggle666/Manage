package com.hao.onlineExam.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.PagerModel;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.model.vo.ExamTestTypeVO;
import com.hao.onlineExam.model.vo.ExamTestVO;
import com.hao.onlineExam.model.vo.SubjectVO;

@Transactional
public interface IExamTestService {
	
	public PagerModel<ExamTestVO> findAllExamTest();
	
	public ExamTestVO getExamTestById(Integer id);
	
	public void editExamTest(ExamTestVO examTest) throws Exception;
	
	public void createExamTest(ExamTestVO examTest) throws Exception;
	
	public void deleteExamTest(Integer id) throws Exception;

	public List<SubjectVO> getSubjectList() throws Exception;

	public List<ExamTestTypeVO> getTestTypeList() throws Exception;

	Map<Integer, List<ExamTestVO>> getTestListBySubject(Integer subId, String userId);

	public List<ExamTestVO> getExamTestBySubId(Integer subId);
	
	public ExamScoreVO calculateScore(List<Integer> answerIds, Map<String, String[]> testMap, String userId, Integer subId);

	public ExamSubject getSubjectById(Integer subId);
}
