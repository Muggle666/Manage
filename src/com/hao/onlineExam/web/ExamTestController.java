package com.hao.onlineExam.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hao.onlineExam.model.PagerModel;
import com.hao.onlineExam.model.vo.CommonMessageResultVO;
import com.hao.onlineExam.model.vo.ExamTestTypeVO;
import com.hao.onlineExam.model.vo.ExamTestVO;
import com.hao.onlineExam.model.vo.SubjectVO;
import com.hao.onlineExam.service.IExamTestService;
import com.hao.onlineExam.service.imp.ExamTestService;

@Controller
@RequestMapping("/teacher")
public class ExamTestController {

	@Autowired
	private IExamTestService examTestService;
	
	@RequestMapping(value = "/toManageTest")
	public String toManageTest(Model model){
		PagerModel<ExamTestVO> pageList = examTestService.findAllExamTest();
		model.addAttribute("pageList", pageList);
		return "teacher/manageTest";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTestById", method = RequestMethod.POST)
	public ExamTestVO getTestById(Integer testId) throws Exception{
		ExamTestVO resultVO = examTestService.getExamTestById(testId);
		return resultVO;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSubjectAndTypes", method = RequestMethod.POST)
	public Map<String, Object> getSubjectAndTypes() throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<SubjectVO> subjectList = examTestService.getSubjectList();
		List<ExamTestTypeVO> testTypeList = examTestService.getTestTypeList();
		resultMap.put("subjectList", subjectList);
		resultMap.put("testTypeList", testTypeList);
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveTest", method = RequestMethod.POST)
	public CommonMessageResultVO saveTest(@Valid ExamTestVO examTestVO, BindingResult bindingResult){
		CommonMessageResultVO resultVO = new CommonMessageResultVO();
		Map<String, String> messageMap = new HashMap<String, String>();
		resultVO.setSuccess(false);
		if(bindingResult.hasErrors()){
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for(ObjectError error : errorList){
				FieldError fe = (FieldError) error;
				messageMap.put(fe.getField(), fe.getDefaultMessage());
			}
			resultVO.setMessageMap(messageMap);
			return resultVO;
		}
		try{
			examTestService.createExamTest(examTestVO);;
			resultVO.setSuccess(true);
		} catch (Exception e){
			e.printStackTrace();
			messageMap.put("message", e.getMessage());
			resultVO.setMessageMap(messageMap);
		}
		return resultVO;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateTest", method = RequestMethod.POST)
	public CommonMessageResultVO updateTest(@Valid ExamTestVO examTestVO, BindingResult bindingResult){
		CommonMessageResultVO resultVO = new CommonMessageResultVO();
		Map<String, String> messageMap = new HashMap<String, String>();
		resultVO.setSuccess(false);
		if(bindingResult.hasErrors()){
			List<ObjectError> errorList = bindingResult.getAllErrors();
			for(ObjectError error : errorList){
				FieldError fe = (FieldError) error;
				messageMap.put(fe.getField(), fe.getDefaultMessage());
			}
			resultVO.setMessageMap(messageMap);
			return resultVO;
		}
		try{
			examTestService.editExamTest(examTestVO);
			resultVO.setSuccess(true);
		} catch (Exception e){
			e.printStackTrace();
			messageMap.put("message", e.getMessage());
			resultVO.setMessageMap(messageMap);
		}
		return resultVO;
	}
	
	@RequestMapping(value = "/deleteTest/{id}", method = RequestMethod.GET)
	public String deleteTest(RedirectAttributes redirectAttributes, @PathVariable Integer id){
		try{
			examTestService.deleteExamTest(id);
			redirectAttributes.addFlashAttribute("message", "删除成功");
		} catch (Exception e){
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "删除失败");
		}
		return "redirect:/teacher/toManageTest";
	}
	
}
