package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		if(session != null && session.getAttribute("authUser") != null) {	// 장난치는 URL들 전부 main으로 돌린다.
			session.removeAttribute("authUser");
			session.invalidate();
		}
		WebUtil.redirect(request, response, request.getContextPath());
		
	}

}
