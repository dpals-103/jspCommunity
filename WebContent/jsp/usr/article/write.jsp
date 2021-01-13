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
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 작성하기</title>
</head>
<body>
	
	<h1><%=board.getCategory()%> 글 작성하기</h1>
	<hr>
	
	<div>
	<form action="/jspCommunity/usr/article/doWrite" methods="POST" target="_blank">
		<input type="hidden" name="boardId" value="<%=boardId%>" />
		<input type="hidden" name="memberId" value="<%=memberId%>" />
		<hr>
		<input type="text" name="title" placeholder="제목을 입력하세요" maxlength="30"/>
		<hr>
		<textarea name="body" placeholder="내용을 입력해주세요" maxlength="1500"></textarea>
		<hr>
		<input type="submit" value="전송"/>
		<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</form>
	</div>
	
</body>
</html>