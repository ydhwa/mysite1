package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.BoardVo;

public class BoardDao {

	public Boolean insert(BoardVo vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = CustomConnector.getConnection();

			String sql =
						"insert " + 
						"into board " + 
						"values(default, ?, ?, now(), 0, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());

			result = (1 == pstmt.executeUpdate());

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			CustomConnector.closeConnection(null, pstmt, conn);
		}

		return result;
	}
	
	public Boolean update(BoardVo vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = CustomConnector.getConnection();

			String sql =
						"update board " + 
						"set title = ?, contents = ? " + 
						"where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

			result = (1 == pstmt.executeUpdate());

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			CustomConnector.closeConnection(null, pstmt, conn);
		}

		return result;
	}
	
	// updateform, view(update에서는 등록일과 조회수를 볼 필요 없지만 그냥 한번에 처리해버림)
	public BoardVo get(Long no) {
		BoardVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = CustomConnector.getConnection();
			String sql =
					"select u.name, title, contents, to_char(b.reg_date, 'yyyy-mm-dd hh24:mi:ss'), hit, b.user_no " + 
					"from board b, member u " + 
					"where b.user_no = u.no " + 
					"and b.no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				String userName = rs.getString(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);
				Integer hit = rs.getInt(5);
				Long userNo = rs.getLong(6);
				
				result = new BoardVo();
				result.setNo(no);
				result.setUserName(userName);
				result.setTitle(title);
				result.setContents(contents);
				result.setRegDate(regDate);
				result.setViews(hit);
				result.setUserNo(userNo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			CustomConnector.closeConnection(rs, pstmt, conn);
		}
		return result;
	}
	
	public List<BoardVo> getList() {
		List<BoardVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = CustomConnector.getConnection();
			String sql =
					"select b.no, u.name, title, to_char(b.reg_date, 'yyyy-mm-dd hh24:mi:ss'), hit " + 
					"from board b, member u " + 
					"where b.user_no = u.no " + 
					"order by reg_date desc";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long no = rs.getLong(1);
				String userName = rs.getString(2);
				String title = rs.getString(3);
				String regDate = rs.getString(4);
				Integer hit = rs.getInt(5);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setUserName(userName);
				vo.setTitle(title);
				vo.setRegDate(regDate);
				vo.setViews(hit);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			CustomConnector.closeConnection(rs, pstmt, conn);
		}
		return result;
	}
	
	
}
