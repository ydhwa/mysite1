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

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 접근제어(솔직히 addForm에서 거르긴 한다.)
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
		// ----------------------------------------------
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		Long userNo = authUser.getNo();
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(userNo);
		
		new BoardDao().insert(vo);
		
		WebUtil.redirect(request, response, request.getContextPath() + "/board");
	}

}
