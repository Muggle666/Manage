package com.hao.onlineExam.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hao.onlineExam.dao.IExamScoreDAO;
import com.hao.onlineExam.dao.ISubjectDAO;
import com.hao.onlineExam.dao.IUserDAO;
import com.hao.onlineExam.model.ExamScore;
import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.User;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.model.vo.ExamUserVO;
import com.hao.onlineExam.model.vo.SubjectVO;
import com.hao.onlineExam.service.ISubjectService;

@Service
@Transactional
public class SubjectServiceImp implements ISubjectService{

	
	@Autowired
	private ISubjectDAO subjectDAO ;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IExamScoreDAO examScoreDAO;
	
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

	@Override
	public List<ExamUserVO> getAllStudent() {
		List<User> userList = userDAO.findAllUsers();
		List<ExamUserVO> resultList = new ArrayList<ExamUserVO>();
		for(User user : userList){
			ExamUserVO vo = new ExamUserVO();
			BeanUtils.copyProperties(user, vo);
			resultList.add(vo);
		}
		return resultList;
	}

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

	@Override
	public List<SubjectVO> findSubjectsBySubId(Integer subId) {
//		ExamSubject subjectList = subjectDAO.findSubjectsBySubId(subId);
//		ExamScore examScoreList = examScoreDAO.getScoreBySubId(subId);
//		List<SubjectVO> resultList = new ArrayList<SubjectVO>();
//			SubjectVO vo = new SubjectVO();
//			BeanUtils.copyProperties(subjectList, vo);
//			BeanUtils.copyProperties(examScoreList, vo);
//			resultList.add(vo);
//		return resultList;
		return null;
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
