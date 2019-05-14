<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	UserVo vo = (UserVo) request.getAttribute("userVo");
%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%= request.getContextPath() %>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="<%= request.getContextPath() + "/user?a=update" %>">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="<%= vo.getName() %>">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="<%= vo.getEmail() %>">
					<input type="button" value="id 중복체크">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="<%= vo.getPassword() %>">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" <% if("female".equals(vo.getGender())) { %>checked="checked" <% } %>>
						<label>남</label> <input type="radio" name="gender" value="male" <% if("male".equals(vo.getGender())) { %>checked="checked" <% } %>>
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<!-- id값도 함께 보낸다. -->
					<input type="hidden" name="no", value="<%= vo.getNo() %>">
					
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>