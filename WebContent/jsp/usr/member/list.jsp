<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import= "java.util.List"%>
<%@ page import= "java.util.Map"%>

<%
	List<Map<String, Object>> memberMapList = 
	(List<Map<String, Object>>) request.getAttribute("memberMapList");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
</head>
<body>
	<h1>회원리스트</h1>
	<%
	for(Map<String, Object> memberMap : memberMapList){
	%>
	<div>
		번호 : <%=memberMap.get("id")%>
		<br>
		이름 : <%=memberMap.get("name")%>
	</div>
	<%
	}
	%>
</body>
</html>