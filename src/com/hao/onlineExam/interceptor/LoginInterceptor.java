package com.hao.onlineExam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hao.onlineExam.model.User;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null){
			return true;
		}else{
			response.sendRedirect(request.getContextPath() + "/login");
		}
//		System.out.println("--------LoginIntercepter.preHandle()");		
		return false;
	}
	

	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			ModelAndView arg3) throws Exception {
//		System.out.println("--------LoginIntercepter.postHandle()");		
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
//		System.out.println("--------LoginIntercepter.afterCompletion()");
	}

}
