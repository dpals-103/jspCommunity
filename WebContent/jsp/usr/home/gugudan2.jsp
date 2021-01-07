<!-- 필수문구 (언어타입 선언)-->
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <%%>  자바 문구를 사용할때는 이 기호로 감싸준다(출력X)-->
<%
int dan =(int)request.getAttribute("dan");
int limit =(int)request.getAttribute("limit");
%>

<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>구구단 <%=dan%></title>
</head>
<body>
	<h1>
		구구단
		<%=dan%>단
	</h1>
	<%
	for (int i = 1; i<=limit; i++){
	%>
	
	<div><%=dan%> * <%=i%> = <%=dan * i%></div>
	
	<% 
	} 
	%>
</body>
</html>