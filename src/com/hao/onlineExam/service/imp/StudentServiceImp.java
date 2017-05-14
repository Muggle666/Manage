package com.hao.onlineExam.service.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.onlineExam.dao.IExamScoreDAO;
import com.hao.onlineExam.dao.IExamTestDAO;
import com.hao.onlineExam.dao.IExamTestTypeDAO;
import com.hao.onlineExam.dao.ISubjectDAO;
import com.hao.onlineExam.dao.IUserAndSubjectDAO;
import com.hao.onlineExam.model.ExamScore;
import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.ExamTest;
import com.hao.onlineExam.model.ExamTestType;
import com.hao.onlineExam.model.ExamUserSubject;
import com.hao.onlineExam.model.User;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.model.vo.ExamTestTypeVO;
import com.hao.onlineExam.model.vo.ExamTestVO;
import com.hao.onlineExam.model.vo.SubjectVO;
import com.hao.onlineExam.service.IStudentService;

@Service
public class StudentServiceImp implements IStudentService{

	@Autowired
	private IExamScoreDAO examScoreDAO;
	
	@Autowired
	private IUserAndSubjectDAO userAndSubjectDAO;
	
	@Autowired
	private IExamTestDAO examTestDAO;
	
	@Autowired
	private IExamTestTypeDAO examTestTypeDAO;
	
	@Autowired
	private ISubjectDAO subjectDAO;
	
	@Override
	public List<SubjectVO> findAllSubject(User userVO) throws Exception {
		List<SubjectVO> resultList = new ArrayList<SubjectVO>();
//		User user = userDAO.get(userVO.getUserId());
		Collection<ExamUserSubject> subs = userAndSubjectDAO.getSubjectByUserId(userVO.getUserId());
		for(ExamUserSubject subjectList : subs){
			SubjectVO subVO = new SubjectVO();
			BeanUtils.copyProperties(subjectList.getSubject(), subVO);
			ExamScore score = examScoreDAO.getExamScore(userVO.getUserId(),subVO.getSubId());
			if(score != null){
				ExamScoreVO scoreVO = new ExamScoreVO();
				BeanUtils.copyProperties(score, scoreVO);
				Calendar startTime = Calendar.getInstance();
				startTime.setTime(scoreVO.getExamTime());
				Calendar currentTime = Calendar.getInstance();
				currentTime.setTime(new Date());
				long startSecond = startTime.getTimeInMillis();
				long currentSecond = currentTime.getTimeInMillis();
				long totalSecond = subjectList.getSubject().getTotalTime();
				scoreVO.setRemainingExamTime(totalSecond - (currentSecond - startSecond)/1000);
				subVO.setExamScoreVO(scoreVO);
			}
			resultList.add(subVO);
		}
		return resultList;
	}
	
	@Override
	public ExamScoreVO getScoreById(Integer scoreId) {
		ExamScoreVO vo = examScoreDAO.getScoreById(scoreId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		vo.setExamTimeString(df.format(vo.getExamTime()));
		return vo;
	}
	
	/*-----------------------------考试--------------------------------------------------*/

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
/*		ExamSubject subject = examSubjectDAO.get(subId);
		if (subject == null) return null;
		Integer totalTestScore = subject.getTotalScore();
		Integer totalTestCount = subject.getTestCount();
		// 1. 校验科目总分和总题数是否匹配
		if (!TestUtils.checkSubjectScoreAndCount(totalTestCount, totalTestScore, testTypeScoreMap)) return null;
		// 2. 获取各个题型的数量
		Map<Integer, Integer> testTypeCountMap = TestUtils.getTestCountByScore(totalTestCount, testTypeScoreMap);
		// 4. 生成随机题目
		Map<Integer, List<ExamTestVO>> resultTestListMap = new HashMap<Integer, List<ExamTestVO>>();
		for (Integer key : testTypeCountMap.keySet()) {
			Integer count = testTypeCountMap.get(key);
			List<ExamTestVO> testList = testListMap.get(key);
			// 3.1 在里面
			List<Integer> randomTestIdList = TestUtils.getRandomTestIdList(testList, count);
			if (randomTestIdList == null) return null;
			// 根据多个id获取多个题目
			List<ExamTest> randomTestList = examTestDAO.getTestListByIdList(randomTestIdList);
			// 转换List<ExamTest> ==>
			List<ExamTestVO> testVOList = new ArrayList<ExamTestVO>();
			for (ExamTest test : randomTestList) {
				ExamTestVO vo = new ExamTestVO();
				BeanUtils.copyProperties(test, vo);
				ExamTestTypeVO testTypeVO = new ExamTestTypeVO();
				BeanUtils.copyProperties(test.getExamTestType(), testTypeVO);
				vo.setExamTestType(testTypeVO);testVOList.add(vo);
			}
			resultTestListMap.put(key, testVOList);//
		}
		ExamScore score = new ExamScore();
		score.setUserId(userId);
		score.setSubjectId(subId);
		score.setExamTime(new Date().toString());
		// 如果成功取到试题
		examScoreDAO.save(score);
		examScoreDAO.flush();
		return resultTestListMap;*/
	}

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

	@Override
	public ExamSubject getSubjectById(Integer subId) {
		return subjectDAO.get(subId);
	}
	
	@Override
	public SubjectVO getSubjectVOById(String userId, Integer subId) {
		ExamSubject sub = subjectDAO.get(subId);
		SubjectVO subVO = new SubjectVO();
		BeanUtils.copyProperties(sub, subVO);
		
		ExamScore score = new ExamScore();
		score.setUserId(userId);
		score.setSubId(subId);
		score.setExamTime(new Date());
		
		ExamScoreVO scoreVO = new ExamScoreVO();
		BeanUtils.copyProperties(score, scoreVO);
		Calendar startTime = Calendar.getInstance();
		Calendar currentTime = Calendar.getInstance();currentTime.add(Calendar.MINUTE, -1);
		Calendar Deadline = Calendar.getInstance();
		startTime.setTime(score.getExamTime());
		currentTime.setTime(new Date());
		Deadline.add(Calendar.MINUTE, sub.getTotalTime());
		long startMillisecond = startTime.getTimeInMillis();
		long currentMillisecond = currentTime.getTimeInMillis();
		long totalSecond = sub.getTotalTime() * 60;
		scoreVO.setRemainingExamTime(totalSecond - (currentMillisecond - startMillisecond) / 1000);
		scoreVO.setDeadline(Deadline.getTime());
		
		subVO.setExamScoreVO(scoreVO);
		examScoreDAO.save(score);
		return subVO;
	}

}
