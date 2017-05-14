package com.hao.onlineExam.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hao.onlineExam.excel.ExcelUtils;
import com.hao.onlineExam.model.PagerModel;
import com.hao.onlineExam.model.vo.CommonMessageResultVO;
import com.hao.onlineExam.model.vo.ExamScoreVO;
import com.hao.onlineExam.model.vo.ExamTestTypeVO;
import com.hao.onlineExam.model.vo.ExamTestVO;
import com.hao.onlineExam.model.vo.ExamUserVO;
import com.hao.onlineExam.model.vo.SubjectVO;
import com.hao.onlineExam.service.ITeacherService;

@Controller
@RequestMapping("teacher")
public class teacherController {
	
	@Autowired
	private ITeacherService teacherService;
	
	//批量导入学生
	@ResponseBody
	@RequestMapping(value = "/uploadFile")
	public CommonMessageResultVO uploadFile(@RequestParam(value = "uploadFile") MultipartFile[] uploadFile,HttpSession session,Model model) throws NoSuchAlgorithmException{
		CommonMessageResultVO resultVO = new CommonMessageResultVO();
		MultipartFile uploadSimpleFile = uploadFile[0];
		String fileName = uploadSimpleFile.getOriginalFilename();
		
		String postfix = fileName.substring(fileName.lastIndexOf("."));
		Map<String,String> messageMap = new HashMap<String,String>();
		
		
			try {
				if(ExcelUtils.OFFICE_EXCEL_2007_POSTFIX.equals(postfix) || ExcelUtils.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){
					teacherService.saveStudentByExcel(uploadSimpleFile.getInputStream(), postfix);
				resultVO.setSuccess(true);
			} else{
				messageMap.put("message", "文件格式不正确");
				resultVO.setMessageMap(messageMap);
			}
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				messageMap.put("message", "批量导入失败");
				resultVO.setMessageMap(messageMap);
			}
		return resultVO;
	}

	//显示管理学生页面
	@RequestMapping("/usersList")
	public String users(Model model){
		List<ExamUserVO> list = teacherService.findAllUsers();
		model.addAttribute("userList", list);
		return "teacher/manageStudent";
	}
	
	//管理学生删除
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public String deleteTest(RedirectAttributes redirectAttributes, @PathVariable String userId){
		try{
			teacherService.deleteUser(userId);
			redirectAttributes.addFlashAttribute("message", "删除成功");
		} catch (Exception e){
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "删除失败");
		}
		return "redirect:/teacher/usersList";
	}
	
	//管理学生保存
	@ResponseBody
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public CommonMessageResultVO save(@Valid ExamUserVO examUserVO, BindingResult bindingResult){
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
			examUserVO.setPassword("123456");
			teacherService.createUser(examUserVO);
			resultVO.setSuccess(true);
		} catch (Exception e){
			e.printStackTrace();
			messageMap.put("message", e.getMessage());
			resultVO.setMessageMap(messageMap);
		}
		return resultVO;
	}
	
	//管理学生修改
	@ResponseBody
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public CommonMessageResultVO edit(@Valid ExamUserVO examUserVO, BindingResult bindingResult){
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
			examUserVO.setPassword(teacherService.getUserById(examUserVO.getUserId()).getPassword());
			teacherService.editUser(examUserVO);
			resultVO.setSuccess(true);
		} catch (Exception e){
			e.printStackTrace();
			messageMap.put("message", e.getMessage());
			resultVO.setMessageMap(messageMap);
		}
		return resultVO;
	}
	
	
	/*-------------------------ExamTestController----------------------------------------*/
	@RequestMapping(value = "/toManageTest")
	public String toManageTest(Model model){
		PagerModel<ExamTestVO> pageList = teacherService.findAllExamTest();
		model.addAttribute("pageList", pageList);
		return "teacher/manageTest";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTestById", method = RequestMethod.POST)
	public ExamTestVO getTestById(Integer testId) throws Exception{
		ExamTestVO resultVO = teacherService.getExamTestById(testId);
		return resultVO;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSubjectAndTypes", method = RequestMethod.POST)
	public Map<String, Object> getSubjectAndTypes() throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<SubjectVO> subjectList = teacherService.getSubjectList();
		List<ExamTestTypeVO> testTypeList = teacherService.getTestTypeList();
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
			teacherService.createExamTest(examTestVO);;
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
			teacherService.editExamTest(examTestVO);
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
			teacherService.deleteExamTest(id);
			redirectAttributes.addFlashAttribute("message", "删除成功");
		} catch (Exception e){
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "删除失败");
		}
		return "redirect:/teacher/toManageTest";
	}
	/*----------------------manageSubjectController---------------------------------*/
	@RequestMapping("/toManageSubject")
	public String users2(Model model) {
		List<SubjectVO> list = teacherService.findAllSubjects();
		model.addAttribute("subjectList", list);
		return "teacher/manageSubject";
	}

	//管理考试科目新增
	@ResponseBody
	@RequestMapping(value = "/saveSubject", method = RequestMethod.POST)
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
			teacherService.createSubject(subjectVO);
			resultVO.setSuccess(true);
		} catch (Exception e){
			e.printStackTrace();
			messageMap.put("message", e.getMessage());
			resultVO.setMessageMap(messageMap);
		}
		return resultVO;
	}
	
	//管理考试科目修改
	@ResponseBody
	@RequestMapping(value = "/editSubject", method = RequestMethod.POST)
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
			teacherService.editSubject(subjectVO);
			resultVO.setSuccess(true);
		} catch (Exception e){
			e.printStackTrace();
			messageMap.put("message", e.getMessage());
			resultVO.setMessageMap(messageMap);
		}
		return resultVO;
	}
	
	//取得所有的学生
	@ResponseBody
	@RequestMapping(value = "/getAllStudent")
	public Map<String,Object> getAllStudent(Integer subId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
//		resultMap.put("allUser", subjectService.getAllUserBySubject(subId));
		resultMap.put("allUser", teacherService.findAllUsers());
		resultMap.put("selectedUser", teacherService.getAllUserBySubject(subId));
		return resultMap;
	}
	
	
	//改变状态
	@ResponseBody
	@RequestMapping(value = "/changeStatus")
	public Map<String,Object> changeStatus(Integer status,Integer subId){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("success", false);
		try{
			teacherService.getStatusBySubject(status,subId);
			resultMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}
	
	//点击关联学生然后保存
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
			teacherService.saveSubjectStudent(list, subId);
		resultMap.put("success", true);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}

	//管理考试科目删除
	@RequestMapping(value = "/deleteSubject/{subId}", method = RequestMethod.GET)
	public String delete(RedirectAttributes redirectAttributes, Model model, @PathVariable Integer subId) {
		try {
			teacherService.deleteSubject(subId);
			// redirectAttributes.addFlashAttribute("message", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/teacher/toManageSubject";
	}
	/*------------------------------查询成绩----------------------------------------------------*/
	@RequestMapping("/toManageScore")
	public String toManageScore(Model model, @RequestParam(required=false)Map<String, String> conditionMap){
		PagerModel<ExamScoreVO> pageList = teacherService.getAllExamScore(conditionMap);
		model.addAttribute("pageList", pageList);
		return "teacher/queryScore";
	}
}
