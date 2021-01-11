<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.List" %>
	<%@ page import="java.util.Map" %>
	<%@ page import="jspCommunity.dto.Article" %>

<%
	int memberId = (int)request.getAttribute("memberId");
	int boardId = (int)request.getAttribute("boardId");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 작성하기</title>
</head>
<body>
	
	<h1>Posting</h1>
	<hr>
	<form action="/jspCommunity/usr/article/doWrite" methods="POST" target="_blank">
		<input type="hidden" name="boardId" value="<%=boardId%>" />
		<input type="hidden" name="memberId" value="<%=memberId%>" />
		<hr>
		<input type="text" name="title" placeholder="제목을 입력하세요" maxlength="30"/>
		<hr>
		<textarea name="body" placeholder="내용을 입력해주세요" maxlength="1500"></textarea>
		<hr>
		<input type="submit" value="전송"/>	
	</form>
	
</body>
</html>