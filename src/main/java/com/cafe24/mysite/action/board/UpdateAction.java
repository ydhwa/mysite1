package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.dao.GuestbookDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 접근제어(어차피 updateform에서 거르긴 함)
		HttpSession session = request.getSession();
		if (session == null) {
			WebUtil.forward(request, response, request.getContextPath());
			return;
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			WebUtil.forward(request, response, request.getContextPath());
			return;
		}

		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo vo = new BoardDao().get(no);
		// 글의 주인이 아니면 수정 권한 없음
		if (authUser.getNo() != vo.getUserNo()) {
			WebUtil.forward(request, response, request.getContextPath());
			return;
		}
		// ----------------------------------------------

		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		BoardVo newVo = new BoardVo();
		newVo.setTitle(title);
		newVo.setContents(contents);
		newVo.setNo(no);
		
		new BoardDao().update(newVo);
		
		WebUtil.redirect(request, response, request.getContextPath() + "/board?a=updatesuccess");
	}

}
