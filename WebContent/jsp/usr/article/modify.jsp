<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List" %>
	<%@ page import="java.util.Map" %>
	<%@ page import="jspCommunity.dto.Article" %>
	<%@ page import="jspCommunity.dto.Board" %>

<%
	int memberId = (int)request.getAttribute("memberId");
	int boardId = (int)request.getAttribute("boardId");
	int id = (int)request.getAttribute("id");
	Board board = (Board)request.getAttribute("board"); 
	Article article = (Article)request.getAttribute("article"); 
	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 수정하기</title>
</head>
<body>
	
	<h1>글 수정하기</h1>
	<hr>
	
	<div>
	<form action="/jspCommunity/usr/article/doModify" methods="POST" target="_blank">
		<input type="hidden" name="boardId" value="<%=boardId%>" />
		<input type="hidden" name="id" value="<%=id%>" />
		<input type="hidden" name="memberId" value="<%=memberId%>" />
		<hr>
		<input type="text" name="title" value=<%=article.title%> maxlength="30"/>
		<hr>
		<textarea name="body" maxlength="1500"><%=article.body%></textarea>
		<hr>
		<input type="submit" value="전송"/>
		<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</form>
	</div>
	
</body>
</html>