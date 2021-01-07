<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>

<%
/*List<Map<String, Object>> allArticleList = 
(List<Map<String, Object>>) request.getAttribute("allArticleList");*/

List<Map<String, Object>> articleList = 
(List<Map<String, Object>>) request.getAttribute("articleList");

String category = (String)request.getAttribute("category");
String boardCode = (String)request.getAttribute("boardCode");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=category%>게시물리스트</title>
</head>
<body>
	<h1><%=category%>게시물리스트 </h1>
	
	<%
	for( Map<String,Object> articleMapList : articleList){
	%>
	<div>
	번호 : <%=articleMapList.get("id") %> / 제목 : <%=articleMapList.get("title") %> / 내용 : <%=articleMapList.get("body") %> 
	</div>
	<% 
	}
	%>
</body>
</html>