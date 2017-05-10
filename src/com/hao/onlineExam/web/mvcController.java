package com.hao.onlineExam.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import com.hao.onlineExam.model.User;
import com.hao.onlineExam.model.vo.CommonMessageResultVO;
import com.hao.onlineExam.model.vo.ExamUserVO;
import com.hao.onlineExam.service.IUserService;

@Controller
@RequestMapping("teacher")
public class mvcController {

	@Autowired
	private IUserService userService;

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
				userService.saveStudentByExcel(uploadSimpleFile.getInputStream(), postfix);
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

	@RequestMapping("/users")
	public String users(Model model){
		List<ExamUserVO> list = userService.findAllUsers();
		model.addAttribute("userList", list);
		return "user/manageStudent";
	}
	
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
	public String deleteTest(RedirectAttributes redirectAttributes, @PathVariable String userId){
		try{
			userService.deleteUser(userId);
			redirectAttributes.addFlashAttribute("message", "删除成功");
		} catch (Exception e){
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "删除失败");
		}
		return "redirect:/teacher/users";
	}
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
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
			userService.createUser(examUserVO);
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
			userService.editUser(examUserVO);
			resultVO.setSuccess(true);
		} catch (Exception e){
			e.printStackTrace();
			messageMap.put("message", e.getMessage());
			resultVO.setMessageMap(messageMap);
		}
		return resultVO;
	}
}
