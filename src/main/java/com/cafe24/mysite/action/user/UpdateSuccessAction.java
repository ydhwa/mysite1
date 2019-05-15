package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mysite.vo.UserVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class UpdateSuccessAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 수정 후 바로 반영을 위함
		HttpSession session = request.getSession();
		UserVo vo = (UserVo) session.getAttribute("authUser");
		session.setAttribute("authUser", vo);
		
		WebUtil.forward(request, response, "/WEB-INF/views/user/updatesuccess.jsp");
	}

}
