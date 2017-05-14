package com.hao.onlineExam.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.User;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.model.vo.SubjectVO;
import com.hao.onlineExam.service.IStudentService;

@Controller
@RequestMapping("student")
public class studentController {
	
	@Autowired
	private IStudentService studentService;
	
	@RequestMapping("/examSubjectList")
	public String show(Model model,HttpSession session) throws Exception {
		User userVO = (User) session.getAttribute("user");
		List<SubjectVO> list = studentService.findAllSubject(userVO);
		model.addAttribute("subjectList", list);
		return "student/manageExamSubjectList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/showScore",method = RequestMethod.POST)
	public ExamScoreVO showScore(HttpSession session,Integer scoreId){
		ExamScoreVO vo = studentService.getScoreById(scoreId);
		return vo;
	}
	
	//@ResponseBody
	@RequestMapping(value = "/toTest/{subId}",method = RequestMethod.GET)
	public String test(HttpSession session,Model model,@PathVariable Integer subId) throws Exception {
		model.addAttribute("testList", studentService.getTestListBySubject(subId,
									((User)session.getAttribute("user")).getUserId()));
		model.addAttribute("subject", 
				studentService.getSubjectVOById(((User)session.getAttribute("user")).getUserId(), subId));
		return "student/test";
	}
	
	@RequestMapping(value = "/assignment",method = RequestMethod.POST)
	public String assignMent(HttpServletRequest request,Integer subId,Model model,RedirectAttributes redirectAttributes){
		Map<String,String[]> parameterMap = request.getParameterMap();
		Set<Entry<String,String[]>> entrySet = parameterMap.entrySet();
		List<Integer> answerIds = new ArrayList<Integer>();
		Map<String,String[]> testMap = new HashMap<String, String[]>();
		if(entrySet != null){
			Iterator<Entry<String,String[]>> it = entrySet.iterator();
			final String TEST_PREFIX = "test";
			while(it.hasNext()){
				Entry<String,String[]> entry = it.next();
				String key = entry.getKey();
				String[] splitArrayString = key.split("_");
				if(splitArrayString.length == 2){
					if(TEST_PREFIX.equals(splitArrayString[0])){
						answerIds.add(Integer.valueOf(splitArrayString[1]));
						testMap.put(splitArrayString[1], entry.getValue());
					}
				}
			}
			ExamScoreVO score = studentService.calculateScore(answerIds,testMap,((User)request.getSession().getAttribute("user")).getUserId(),subId);
			ExamSubject subjectVO = studentService.getSubjectById(subId);
			redirectAttributes.addFlashAttribute("score",score);
			redirectAttributes.addFlashAttribute("subject",subjectVO);
			return "redirect:/student/examSubjectList";
		}
		return null;
	}
}
