package com.hao.onlineExam.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hao.onlineExam.model.ExamSubject;
import com.hao.onlineExam.model.User;
import com.hao.onlineExam.model.vo.CommonMessageResultVO;
import com.hao.onlineExam.model.vo.ExamUserVO;
import com.hao.onlineExam.model.vo.SubjectVO;
import com.hao.onlineExam.service.ISubjectService;
import com.hao.onlineExam.service.IUserService;

@Controller
public class manageSubjectController {

	@Autowired
	private ISubjectService subjectService;
	
	@Autowired
	private IUserService userService;

	@RequestMapping("/subject")
	public String users(Model model) {
		List<SubjectVO> list = subjectService.findAllSubjects();
		model.addAttribute("subjectList", list);
		return "manageSubject/manageSubject";
	}

	//新增
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public CommonMessageResultVO save(@Valid SubjectVO subjectVO, BindingResult bindingResult){
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
			subjectService.createSubject(subjectVO);
			resultVO.setSuccess(true);
		} catch (Exception e){
			e.printStackTrace();
			messageMap.put("message", e.getMessage());
			resultVO.setMessageMap(messageMap);
		}
		return resultVO;
	}
	
	//修改
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public CommonMessageResultVO edit(@Valid SubjectVO subjectVO, BindingResult bindingResult){
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
			subjectService.editSubject(subjectVO);
			resultVO.setSuccess(true);
		} catch (Exception e){
			e.printStackTrace();
			messageMap.put("message", e.getMessage());
			resultVO.setMessageMap(messageMap);
		}
		return resultVO;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getAllStudent")
	public Map<String,Object> getAllStudent(Integer subId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
//		resultMap.put("allUser", subjectService.getAllUserBySubject(subId));
		resultMap.put("allUser", userService.findAllUsers());
		resultMap.put("selectedUser", subjectService.getAllUserBySubject(subId));
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/changeStatus")
	public Map<String,Object> changeStatus(Integer status,Integer subId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("success", false);
		try{
			subjectService.getStatusBySubject(status,subId);
			resultMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveSubjectStudents")
	public Map<String,Object> saveSubjectStudents(@RequestParam(name = "subjectStudents[]")String[] subjectStudents,Integer subId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("success", false);
		List<String> list = new ArrayList<String>();
		for(String student : subjectStudents){
			list.add(student);
		}
		try{
		subjectService.saveSubjectStudent(list, subId);
		resultMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}
//	@RequestMapping(value = "/subjectAdd", method = RequestMethod.GET)
//	public String toAdd(Model model) {
//		model.addAttribute("user", new ExamSubject());
//		return "manageSubject/add";
//	}
//
//	@RequestMapping(value = "/subjectAdd", method = RequestMethod.POST)
//	public String add(RedirectAttributes redirectAttributes, @Validated ExamSubject subject, BindingResult result) {
//		if (result.hasErrors()) {
//			return "manageSubject/add";
//		}
//		try {
//			subjectService.createSubject(subject);
//			redirectAttributes.addFlashAttribute("message", "添加成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//		return "redirect:/subject";
//	}

//	@RequestMapping(value = "/subjectEdit/{subId}", method = RequestMethod.GET)
//	public String toEdit(Model model, @PathVariable Integer subId) {
//		try {
//			ExamSubject subject = subjectService.getSubjectById(subId);
//			model.addAttribute("user", subject);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "manageSubject/edit";
//	}
//
//	@RequestMapping(value = "/subjectEdit/{subId}", method = RequestMethod.POST)
//	public String edit(RedirectAttributes redirectAttributes, @Validated ExamSubject subject, BindingResult result,
//			@PathVariable Integer subId) {
//		if (result.hasErrors()) {
//			return "manageSubject/edit";
//		}
//		try {
//			subject.setSubId(subId);
//			subjectService.editSubject(subject);
//			redirectAttributes.addFlashAttribute("message", "修改成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//		return "redirect:/subject";
//	}

	@RequestMapping(value = "/delete/{subId}", method = RequestMethod.GET)
	public String delete(RedirectAttributes redirectAttributes, Model model, @PathVariable Integer subId) {
		try {
			subjectService.deleteSubject(subId);
			// redirectAttributes.addFlashAttribute("message", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/subject";
	}

}
