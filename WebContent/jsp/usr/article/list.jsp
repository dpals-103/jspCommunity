<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List" %>
	<%@ page import="java.util.Map" %>
	<%@ page import="jspCommunity.dto.Article" %>

<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 리스트</title>
</head>
<body>
	
	<%
		for (Article article : articles) {
	%>
	<div>
	
	게시판 : <%=article.extra__category %><br>
	번호 : <%=article.id %><br>
	작성자 : <%=article.extra__writer %><br>
	제목 : <%=article.title %><br>
	작성일 : <%=article.regDate %><br>
	<hr>

	</div>
	<%
		}
	%>
</body>
</html>