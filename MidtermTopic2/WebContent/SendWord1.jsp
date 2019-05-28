<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SendWord1</title>
</head>
<body>
	<h1>SendWord1</h1>
	<%
		/* 送值出去 */
		request.setAttribute("cat", 10);
		request.setAttribute("cat", 35);
		RequestDispatcher requestDispatcherSuccess = request.getRequestDispatcher("GetWord2.jsp");
		requestDispatcherSuccess.forward(request, response);
	%>
	<!--<a href="GetWord2.jsp">GetWord2</a>-->
</body>
</html>