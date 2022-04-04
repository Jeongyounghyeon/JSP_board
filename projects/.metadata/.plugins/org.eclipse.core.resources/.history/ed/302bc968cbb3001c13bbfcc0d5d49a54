<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "user.UserDAO" %>				<!-- user package에서 UserDAO 불러오기 -->
<%@ page import = "java.io.PrintWriter" %>		<!-- javascript 문장을 사용 -->
<% request.setCharacterEncoding("UTF-8");  %>	<!-- 불러오는 데이터를 UTF-8로 변형 -->
<jsp:useBean id = "user" class = "user.User" scope = "page" />	<!-- JavaBean 사용 scope = "사용 공간(현재 page)"-->
<jsp:setProperty name = "user" property = "userID" />			
<jsp:setProperty name = "user" property = "userPassword" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		UserDAO userDAO = new UserDAO(); 
		int result = userDAO.login(user.getUserID(), user.getUserPassword());
		if (result == 1) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'main.jsp'");
			script.println("</script>");
		}
		else if (result == 0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if (result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if (result == -2){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	%>
</body>
</html>