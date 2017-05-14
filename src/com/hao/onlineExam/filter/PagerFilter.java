package com.hao.onlineExam.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.hao.onlineExam.model.SystemContext;

@WebFilter(urlPatterns="/*",initParams={@WebInitParam(name="pageSize",value="5")})
public class PagerFilter implements Filter {

	private int pageSize;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		int offset = 0 ;
		try {
				offset = Integer.parseInt(request.getParameter("pager.offset"));
			} catch (NumberFormatException e) {
			}try {
			    SystemContext.setOffset(offset);
			    SystemContext.setSize(pageSize);
			    chain.doFilter(request, response);
			} finally {
				SystemContext.removeOffset();
				SystemContext.removeSize();
			}
		}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			pageSize = Integer.parseInt(filterConfig.getInitParameter("pageSize"));
		} catch (NumberFormatException e) {
			pageSize = 5;
		}
	}

}
