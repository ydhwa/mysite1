package com.cafe24.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.action.main.MainActionFactory;
import com.cafe24.web.mvc.Action;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 생명주기 알아보기
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Main: init called");
		super.init();
	}
	public void destroy() {
		System.out.println("Main: destroy called");
		super.destroy();
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Main: service called");
		super.service(request, response);
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("MainServlet: doGetCalled");
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter pw = response.getWriter();	// PrintWriter도 주지만 빈 개행도 하나 더 들어감(=body가 시작함을 알림)
		
//		WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
		String actionName = request.getParameter("a");
		Action action = new MainActionFactory().getAction(actionName);
		action.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
