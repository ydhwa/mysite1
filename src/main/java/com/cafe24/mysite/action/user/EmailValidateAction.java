package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.UserDao;
import com.cafe24.web.mvc.Action;

public class EmailValidateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		Boolean validate = new UserDao().isEmailValidate(email);
		
		request.setAttribute("validate", validate ? "true" : "false");
//		WebUtil.redirect(request, response, request.getContextPath() + "/user?a=updatesuccess");
	}

}
