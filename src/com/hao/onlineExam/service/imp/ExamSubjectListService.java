package com.hao.onlineExam.service.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.onlineExam.dao.IExamScoreDAO;
import com.hao.onlineExam.dao.IExamSubjectListDAO;
import com.hao.onlineExam.dao.IUserAndSubjectDAO;
import com.hao.onlineExam.dao.IUserDAO;
import com.hao.onlineExam.model.ExamScore;
import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.ExamUserSubject;
import com.hao.onlineExam.model.User;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.model.vo.ExamUserVO;
import com.hao.onlineExam.model.vo.SubjectVO;
import com.hao.onlineExam.service.IExamSubjectListService;

@Service
public class ExamSubjectListService implements IExamSubjectListService {

	@Autowired
	private IExamSubjectListDAO listDAO;
	
	@Autowired
	private IExamScoreDAO examScoreDAO;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IUserAndSubjectDAO userAndSubjectDAO;

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

}
