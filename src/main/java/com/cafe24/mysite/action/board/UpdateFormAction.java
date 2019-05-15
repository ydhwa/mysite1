package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 접근제어
		HttpSession session = request.getSession();
		if(session == null) {
			WebUtil.forward(request, response, request.getContextPath());
			return ;
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			WebUtil.forward(request, response, request.getContextPath());
			return ;
		}
		
		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo vo = new BoardDao().get(no);
		// 글의 주인이 아니면 수정 권한 없음
		if(authUser.getNo() != vo.getUserNo()) {
			WebUtil.forward(request, response, request.getContextPath());
			return ;
		}
		// ----------------------------------------------
		
		request.setAttribute("vo", vo);
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/update.jsp");
	}

}
