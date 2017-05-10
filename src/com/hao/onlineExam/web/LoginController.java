package com.hao.onlineExam.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hao.onlineExam.model.User;
import com.hao.onlineExam.model.vo.ExamUserVO;
import com.hao.onlineExam.service.IUserService;
import com.hao.onlineExam.util.MD5Utils;

@Controller
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin(Model model, HttpSession session){
		session.removeAttribute("user");
		model.addAttribute("userModel", new User());
		return "login/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(Model model, User user, HttpSession session, RedirectAttributes redirectAttributes) throws Exception{
		if(user != null){
			User resultUser = userService.getByUserName(user.getUserName());
			if(resultUser != null){
				if(resultUser.getPassword() != null){
					String encodePassword = MD5Utils.getMD5Code(user.getPassword());
					Date currentTime = null;
					Date lastLockTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(resultUser.getLast_lock_time());
					Calendar currentTimeCalendar = Calendar.getInstance();
					currentTimeCalendar.setTime(new Date());
					currentTimeCalendar.add(Calendar.MINUTE, -5);
					currentTime = currentTimeCalendar.getTime();
					if(lastLockTime.before(currentTime)){
						if(encodePassword.equals(resultUser.getPassword())){
							resultUser.setLogin_fail_count(0);
							userService.editUser(resultUser);
							session.setAttribute("user", resultUser);
							return "redirect:/teacher/users";
						}else{
							resultUser.setLogin_fail_count(resultUser.getLogin_fail_count() + 1);
							if(resultUser.getLogin_fail_count() >= 3){
								resultUser.setLogin_fail_count(0);
								resultUser.setLast_lock_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
								userService.editUser(resultUser);
							}
							userService.editUser(resultUser);
							redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
							return "redirect:/login";
						}
					}else{
						redirectAttributes.addFlashAttribute("message", "账户被锁定，请稍后再登录");
						return "redirect:/login";
					}
				}
			}
		}
		redirectAttributes.addFlashAttribute("message", "用户名或密码错误");
		return "redirect:/login";
	}
	
}
