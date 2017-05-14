package com.hao.onlineExam.service.imp;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.onlineExam.dao.IExamScoreDAO;
import com.hao.onlineExam.dao.IExamTestDAO;
import com.hao.onlineExam.dao.IExamTestTypeDAO;
import com.hao.onlineExam.dao.ISubjectDAO;
import com.hao.onlineExam.dao.IUserDAO;
import com.hao.onlineExam.excel.ExcelUtils;
import com.hao.onlineExam.model.ExamScore;
import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.ExamTest;
import com.hao.onlineExam.model.ExamTestType;
import com.hao.onlineExam.model.PagerModel;
import com.hao.onlineExam.model.User;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.model.vo.ExamTestTypeVO;
import com.hao.onlineExam.model.vo.ExamTestVO;
import com.hao.onlineExam.model.vo.ExamUserVO;
import com.hao.onlineExam.model.vo.SubjectVO;
import com.hao.onlineExam.service.ITeacherService;

@Service
public class TeacherServiceImp implements ITeacherService{
	
	@Autowired
	private IUserDAO userDAO ;
	
	@Autowired
	private ISubjectDAO subjectDAO ;
	
	@Autowired
	private IExamScoreDAO examScoreDAO;
	
	@Autowired
	private IExamTestDAO examTestDAO;
	
	@Autowired
	private IExamTestTypeDAO examTestTypeDAO;
	
	@Override
	public void saveStudentByExcel(InputStream inputStream, String postfix) throws NoSuchAlgorithmException {
		Workbook wk = ExcelUtils.openWorkbook(inputStream, postfix);
		List<ExamUserVO> allExcelUser = ExcelUtils.getStudentListByExcel(wk);
		List<User> entities = new ArrayList<User>();
		if(allExcelUser != null){
//			ExamRole role = examRoleDAO.get(25);
			User entity = null;
			for(int i = 0;i<allExcelUser.size();i++){
				entity = new User();
				BeanUtils.copyProperties(allExcelUser.get(i),entity);
				if("男".equals(allExcelUser.get(i).getGender())){
					entity.setGender(0);
				}else if("女".equals(allExcelUser.get(i).getGender())){
					entity.setGender(1);
				}
//				Set<ExamRole> examRoles = new HashSet<ExamRole>();
//				examRoles.add(role);
//				entity.setExamRoles(examRoles);
				entities.add(entity);
			}
		}
		userDAO.save(entities);
	}
	
	@Override
	public List<ExamUserVO> findAllUsers() {
		List<User> userList = userDAO.findAllUsers();
		List<ExamUserVO> userVOList = new ArrayList<ExamUserVO>();
		for(User user : userList){
			ExamUserVO vo = new ExamUserVO();
			BeanUtils.copyProperties(user, vo);
			if(user.getGender() == 0) {
				vo.setGender("男");
			}
			if(user.getGender() == 1){
				vo.setGender("女");
			} 
			userVOList.add(vo);
		}
			
		return userVOList;
	}

	@Override
	public void createUser(ExamUserVO examUserVO) throws Exception{
		User user = new User();
		BeanUtils.copyProperties(examUserVO, user);
		if("男".equals(examUserVO.getGender())){
			user.setGender(0);
		}else if("女".equals(examUserVO.getGender())){
			user.setGender(1);
		}
		userDAO.createUser(user);
	}

	@Override
	public void editUser(ExamUserVO examUserVO) throws Exception {
		User user = new User();
		BeanUtils.copyProperties(examUserVO, user);
		if("男".equals(examUserVO.getGender())){
			user.setGender(0);
		}else if("女".equals(examUserVO.getGender())){
			user.setGender(1);
		}
		userDAO.editUser(user);
	}

	@Override
	public User getUserById(String userId) throws Exception {
		return userDAO.getUserById(userId);
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		userDAO.deleteUser(userId);
	}

//	@Override
//	public List<ExamUserVO> getUser() throws Exception {
//		List<ExamUserVO> userVOList = new ArrayList<ExamUserVO>();
//		List<User> userList = userDAO.findAllUsers();
//		for(User user : userList){
//			ExamUserVO vo = new ExamUserVO();
//			BeanUtils.copyProperties(user, vo);
//			userVOList.add(vo);
//		}
//		return userVOList;
//	}

//	@Override
//	public User getByUserName(String userName) {
//		return (User) userDAO.createQuery("From User Where userName = ?").setParameter(0, userName).uniqueResult();
//	}

	@Override
	public void editUser(User user) throws Exception {
		userDAO.editUser(user);
	}
	
	/*-------------------------------------------管理科目--------------------------------------------------------------*/
	
	
	@Override
	public List<SubjectVO> findAllSubjects() {
		List<ExamSubject> subjectList = subjectDAO.loadAll();
		List<SubjectVO> resultList = new ArrayList<SubjectVO>();
		for(ExamSubject subject : subjectList){
			SubjectVO vo = new SubjectVO();
			BeanUtils.copyProperties(subject, vo);
			resultList.add(vo);
		}
		return resultList;
	}

	@Override
	public void createSubject(SubjectVO subjectVO) throws Exception {
		ExamSubject subject = new ExamSubject();
		BeanUtils.copyProperties(subjectVO, subject);
		subjectDAO.createSubject(subject);
	}

	@Override
	public void editSubject(SubjectVO subjectVO) throws Exception {
		ExamSubject subject = new ExamSubject();
		BeanUtils.copyProperties(subjectVO, subject);
		subjectDAO.editSubject(subject);
	}

	@Override
	public ExamSubject getSubjectById(Integer subId) throws Exception {
		return subjectDAO.getSubjectById(subId);
	}

	@Override
	public void deleteSubject(Integer subId) throws Exception {
		subjectDAO.deleteSubject(subId);
	}

	@Override
	public Map<String, Object> getAllStudent(Integer subId) {
		return subjectDAO.getAllStudent(subId);
	}

	@Override
	public List<String> getAllUserBySubject(Integer subId) {
		return subjectDAO.getAllUserBySubject(subId);
	}

//	@Override
//	public List<ExamUserVO> getAllStudent() {
//		List<User> userList = userDAO.findAllUsers();
//		List<ExamUserVO> resultList = new ArrayList<ExamUserVO>();
//		for(User user : userList){
//			ExamUserVO vo = new ExamUserVO();
//			BeanUtils.copyProperties(user, vo);
//			resultList.add(vo);
//		}
//		return resultList;
//	}

	@Override
	public void saveSubjectStudent(List<String> subjectStudents, Integer subId) throws RuntimeException {
		List<String> dbSubjectUserList = subjectDAO.getAllUserBySubject(subId);
		List<String> insertList = new ArrayList<String>();
		List<String> delList = new ArrayList<String>();
		for(String selectedUser : subjectStudents){
			if(!dbSubjectUserList.contains(selectedUser)){
				insertList.add(selectedUser);
			}
		}
		for(String selectedUser : dbSubjectUserList){
			if(!subjectStudents.contains(selectedUser)){
				delList.add(selectedUser);
			}
		}
		subjectDAO.insertSubjectUsers(insertList,subId);
		subjectDAO.deleteSubjectUsers(delList,subId);
	}

	@Override
	public void getStatusBySubject(Integer status,Integer subId) {
		if(status == 0){
			status = 1;
		}else{
			status = 0;
		}
		subjectDAO.getStatusBySubject(status,subId);
	}

//	@Override
//	public List<SubjectVO> findSubjectsBySubId(Integer subId) {
////		ExamSubject subjectList = subjectDAO.findSubjectsBySubId(subId);
////		ExamScore examScoreList = examScoreDAO.getScoreBySubId(subId);
////		List<SubjectVO> resultList = new ArrayList<SubjectVO>();
////			SubjectVO vo = new SubjectVO();
////			BeanUtils.copyProperties(subjectList, vo);
////			BeanUtils.copyProperties(examScoreList, vo);
////			resultList.add(vo);
////		return resultList;
//		return null;
//	}
	
//	@Override
//	public SubjectVO getSubjectVOById(String userId, Integer subId) {
//		ExamSubject sub = subjectDAO.get(subId);
//		SubjectVO subVO = new SubjectVO();
//		BeanUtils.copyProperties(sub, subVO);
//		
//		ExamScore score = new ExamScore();
//		score.setUserId(userId);
//		score.setSubId(subId);
//		score.setExamTime(new Date());
//		
//		ExamScoreVO scoreVO = new ExamScoreVO();
//		BeanUtils.copyProperties(score, scoreVO);
//		Calendar startTime = Calendar.getInstance();
//		Calendar currentTime = Calendar.getInstance();currentTime.add(Calendar.MINUTE, -1);
//		Calendar Deadline = Calendar.getInstance();
//		startTime.setTime(score.getExamTime());
//		currentTime.setTime(new Date());
//		Deadline.add(Calendar.MINUTE, sub.getTotalTime());
//		long startMillisecond = startTime.getTimeInMillis();
//		long currentMillisecond = currentTime.getTimeInMillis();
//		long totalSecond = sub.getTotalTime() * 60;
//		scoreVO.setRemainingExamTime(totalSecond - (currentMillisecond - startMillisecond) / 1000);
//		scoreVO.setDeadline(Deadline.getTime());
//		
//		subVO.setExamScoreVO(scoreVO);
//		examScoreDAO.save(score);
//		return subVO;
//	}

	/*------------------------------------------管理考试--------------------------------------------------------------*/
	
	
	@Override
	public PagerModel<ExamTestVO> findAllExamTest(){
		PagerModel<ExamTest> examTestList = examTestDAO.findAllExamTest();
		if(examTestList != null){
			PagerModel<ExamTestVO> pageList = new PagerModel<ExamTestVO>();
			pageList.setDates(new ArrayList<ExamTestVO>());
			for(ExamTest entity : examTestList.getDates()){
				ExamTestVO vo = new ExamTestVO();
				ExamTestTypeVO typeVO = new ExamTestTypeVO();
				BeanUtils.copyProperties(entity.getExamTestType(), typeVO);
				BeanUtils.copyProperties(entity, vo);
				vo.setExamTestTypeVO(typeVO);
				ExamSubject subject = subjectDAO.get(entity.getSubId());
				if(subject != null) vo.setName(subject.getName());
				pageList.getDates().add(vo);
			}
			pageList.setOffset(examTestList.getOffset());
			pageList.setPageSize(examTestList.getPageSize());
			pageList.setTotalSize(examTestList.getTotalSize());
			return pageList;
		}
		return null;
	}

	@Override
	public ExamTestVO getExamTestById(Integer id) {
		ExamTest entity =  examTestDAO.getExamTestById(id);
		if(entity != null){
			ExamTestVO vo = new ExamTestVO();
			BeanUtils.copyProperties(entity, vo);
			ExamSubject subject = subjectDAO.getSubjectById(entity.getSubId());
			if(subject != null) {
				vo.setName(subject.getName());
			}
			ExamTestTypeVO typeVO = new ExamTestTypeVO();
			BeanUtils.copyProperties(entity.getExamTestType(), typeVO);
			vo.setExamTestTypeVO(typeVO);
			return vo;
		}
		return null;
	}

	@Override
	public void editExamTest(ExamTestVO examTestVO) throws Exception {
		ExamTest examTest = new ExamTest();
		ExamTestType examTestType = examTestTypeDAO.get(examTestVO.getExamTestTypeVO().getId());
		BeanUtils.copyProperties(examTestVO, examTest);
		examTest.setExamTestType(examTestType);
		examTestDAO.editExamTest(examTest);
	}

	@Override
	public void createExamTest(ExamTestVO examTestVO) throws Exception {
		
		ExamTest examTest = new ExamTest();
		ExamTestType examTestType = examTestTypeDAO.get(examTestVO.getExamTestTypeVO().getId());
		BeanUtils.copyProperties(examTestVO, examTest);
		examTest.setId(1);
		examTest.setExamTestType(examTestType);
		examTestDAO.createExamTest(examTest);
	}

	@Override
	public void deleteExamTest(Integer id) throws Exception {
		examTestDAO.delateExamTest(id);		
	}

	@Override
	public List<SubjectVO> getSubjectList() throws Exception {
		List<SubjectVO> subjectVOList = new ArrayList<SubjectVO>();
		List<ExamSubject> subjectList = subjectDAO.findAllSubjects();
		for(ExamSubject subject : subjectList){
			SubjectVO vo = new SubjectVO();
			BeanUtils.copyProperties(subject, vo);
			subjectVOList.add(vo);
		}
		return subjectVOList;
	}

	@Override
	public List<ExamTestTypeVO> getTestTypeList() throws Exception {
		List<ExamTestTypeVO> testTypeVOList = new ArrayList<ExamTestTypeVO>();
		List<ExamTestType> testTypeList = examTestTypeDAO.loadAll();
		for(ExamTestType testType : testTypeList){
			ExamTestTypeVO vo = new ExamTestTypeVO();
			BeanUtils.copyProperties(testType, vo);
			testTypeVOList.add(vo);
		}
		return testTypeVOList;
	}
	
//	@Override
//	public List<ExamTestVO> getExamTestBySubId(Integer subId) {
//		List<ExamTestVO> testList = new ArrayList<ExamTestVO>();
//		List<ExamTest> entity =  examTestDAO.getExamTestBySubId(subId);
//		if(entity != null){
//			for(ExamTest examTest : entity){
//				ExamTestVO vo = new ExamTestVO();
//				BeanUtils.copyProperties(examTest, vo);
//				ExamSubject subject = subjectDAO.getSubjectById(subId);
//				if(subject != null) {
//					vo.setName(subject.getName());
//				}
//				ExamTestTypeVO typeVO = new ExamTestTypeVO();
//				BeanUtils.copyProperties(examTest.getExamTestType(), typeVO);
//				vo.setExamTestTypeVO(typeVO);
//				testList.add(vo);
//			}
//		}
//		return testList;
//	}

	//获取考试题目
	@Override
	public Map<Integer, List<ExamTestVO>> getTestListBySubject(Integer subId, String userId) {
		List<ExamTestType> testTypeList = examTestTypeDAO.find("FROM ExamTestType ");
		Map<Integer, Integer> testTypeScoreMap = new HashMap<Integer, Integer>();// 所有题型各自的分数
		Map<Integer, List<ExamTestVO>> testListMap = new HashMap<Integer, List<ExamTestVO>>();
		// 获取所有题型，得到testTypeScoreMap
		if (testTypeList != null) {
			for (ExamTestType testType : testTypeList) {
				testTypeScoreMap.put(testType.getId(), testType.getTestTypeScore());
				// 3.0. 根据科目获取各个题型所有题目
				List<ExamTest> testList = examTestDAO.getTestListBySubAndTestType(subId, testType.getId());
				List<ExamTestVO> testVOList = new ArrayList<ExamTestVO>();
				// 分别获取各个题型在数据库里的所有题目
				for (ExamTest test : testList) {
					ExamTestVO vo = new ExamTestVO();
					BeanUtils.copyProperties(test, vo);
					ExamTestTypeVO testTypeVO = new ExamTestTypeVO();
					BeanUtils.copyProperties(test.getExamTestType(), testTypeVO);
					vo.setExamTestTypeVO(testTypeVO);
					testVOList.add(vo);
				}
				testListMap.put(testType.getId(), testVOList);//
			}
		} else {
			return null;
		}
		return testListMap;
	}

	//计算成绩
	@Override
	public ExamScoreVO calculateScore(List<Integer> answerIds, Map<String, String[]> testMap, String userId, Integer subId) {
		double score = 0;
		for(Map.Entry<String, String[]> entry : testMap.entrySet()){
			ExamTest test = examTestDAO.get(Integer.valueOf(entry.getKey()));
			if("多选题".equals(test.getExamTestType().getTestType())){
				if(Arrays.equals(entry.getValue(), test.getAnswer().split(","))){
					score += test.getExamTestType().getTestTypeScore();
				}
			}else{
				if((entry.getValue())[0].equals(test.getAnswer())){
					score += test.getExamTestType().getTestTypeScore();
				}
			}
		}
		ExamScore examScore = examScoreDAO.getExamScore(userId, subId);
		examScore.setScore(score);
		ExamScoreVO scoreVO = new ExamScoreVO();
		BeanUtils.copyProperties(examScore, scoreVO);
		examScoreDAO.update(examScore);
		return scoreVO;
	}

//	@Override
//	public ExamSubject getSubjectById(Integer subId) {
//		return subjectDAO.get(subId);
//	}
	
	/*----------------------------------------考试分数-----------------------------------------------------------*/
	
	
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

	@Override
	public PagerModel<ExamScoreVO> getAllExamScore(Map<String, String> conditionMap) {
		return examScoreDAO.getAllExamScore(conditionMap);
	}
	
	/*-------------------------------登陆-------------------------------------------*/
	@Override
	public User getByUserName(String userName) {
		return (User) userDAO.createQuery("From User Where userName = ?").setParameter(0, userName).uniqueResult();
	}
}
