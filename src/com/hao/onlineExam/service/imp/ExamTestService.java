package com.hao.onlineExam.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.hao.onlineExam.model.ExamScore;
import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.ExamTest;
import com.hao.onlineExam.model.ExamTestType;
import com.hao.onlineExam.model.PagerModel;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.model.vo.ExamTestTypeVO;
import com.hao.onlineExam.model.vo.ExamTestVO;
import com.hao.onlineExam.model.vo.SubjectVO;
import com.hao.onlineExam.service.IExamTestService;

@Service
public class ExamTestService implements IExamTestService{

	@Autowired
	private IExamTestDAO examTestDAO;
	
	@Autowired
	private IExamTestTypeDAO examTestTypeDAO;
	
	@Autowired
	private ISubjectDAO subjectDAO;
	
	@Autowired
	private IExamScoreDAO examScoreDAO;
	
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
				ExamSubject subject = subjectDAO.getSubjectById(entity.getSubId());
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
	
	@Override
	public List<ExamTestVO> getExamTestBySubId(Integer subId) {
		List<ExamTestVO> testList = new ArrayList<ExamTestVO>();
		List<ExamTest> entity =  examTestDAO.getExamTestBySubId(subId);
		if(entity != null){
			for(ExamTest examTest : entity){
				ExamTestVO vo = new ExamTestVO();
				BeanUtils.copyProperties(examTest, vo);
				ExamSubject subject = subjectDAO.getSubjectById(subId);
				if(subject != null) {
					vo.setName(subject.getName());
				}
				ExamTestTypeVO typeVO = new ExamTestTypeVO();
				BeanUtils.copyProperties(examTest.getExamTestType(), typeVO);
				vo.setExamTestTypeVO(typeVO);
				testList.add(vo);
			}
		}
		return testList;
	}

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
	
}
