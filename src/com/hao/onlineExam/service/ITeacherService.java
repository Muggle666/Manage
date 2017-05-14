package com.hao.onlineExam.service;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.PagerModel;
import com.hao.onlineExam.model.User;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.model.vo.ExamTestTypeVO;
import com.hao.onlineExam.model.vo.ExamTestVO;
import com.hao.onlineExam.model.vo.ExamUserVO;
import com.hao.onlineExam.model.vo.SubjectVO;

@Transactional
public interface ITeacherService {
public void saveStudentByExcel(InputStream inputStream,String postfix) throws NoSuchAlgorithmException;
	
	public List<ExamUserVO> findAllUsers();
	
	public void createUser(ExamUserVO examUserVO) throws Exception;
	
	public void editUser(ExamUserVO examUserVO) throws Exception;
	
	public void editUser(User user) throws Exception;
	
	public User getUserById(String userId) throws Exception;

	public void deleteUser(String userId) throws Exception;
	
//	public List<ExamUserVO> getUser() throws Exception;
//	
//	public User getByUserName(String userName);
	
	/*----------------------------------------管理科目-----------------------------------------------------------------*/
	public List<SubjectVO> findAllSubjects();
	
//	public List<SubjectVO> findSubjectsBySubId(Integer subId);
	
	public void createSubject(SubjectVO subjectVO) throws Exception;
	
	public void editSubject(SubjectVO subjectVO) throws Exception;
	
	public ExamSubject getSubjectById(Integer userId) throws Exception;

	public void deleteSubject(Integer subId) throws Exception;
	
	public Map<String,Object> getAllStudent(Integer subId);
	
	public List<String> getAllUserBySubject(Integer subId);
	
	//public List<ExamUserVO> getAllStudent();
	
	public void saveSubjectStudent(List<String> subjectStudents,Integer subId) throws RuntimeException;

	public void getStatusBySubject(Integer status,Integer subId);
	
//	public SubjectVO getSubjectVOById(String userId, Integer subId) ;
	
	/*-----------------------------------------管理考试----------------------------------------------------------------------*/
	public PagerModel<ExamTestVO> findAllExamTest();
	
	public ExamTestVO getExamTestById(Integer id);
	
	public void editExamTest(ExamTestVO examTest) throws Exception;
	
	public void createExamTest(ExamTestVO examTest) throws Exception;
	
	public void deleteExamTest(Integer id) throws Exception;

	public List<SubjectVO> getSubjectList() throws Exception;

	public List<ExamTestTypeVO> getTestTypeList() throws Exception;

	Map<Integer, List<ExamTestVO>> getTestListBySubject(Integer subId, String userId);

//	public List<ExamTestVO> getExamTestBySubId(Integer subId);
	
	public ExamScoreVO calculateScore(List<Integer> answerIds, Map<String, String[]> testMap, String userId, Integer subId);

//	public ExamSubject getSubjectById(Integer subId);
	
	/*-------------------------------------------考试分数------------------------------------------------------------------*/
	public List<ExamScoreVO> getAllExamScore();
	
	public double getExamScore(String userId,Integer subId);

	public PagerModel<ExamScoreVO> getAllExamScore(Map<String, String> conditionMap);
	
	/*------------------------------登陆-----------------------------------------*/
	
	public User getByUserName(String userName);
	
}
