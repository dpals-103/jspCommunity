<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="jspCommunity.dto.Article"%>

<%
	Article article = (Article) request.getAttribute("article");
int boardId = (int) request.getAttribute("boardId");
int memberId = (int) request.getAttribute("memberId");
String title = (String) request.getAttribute("title");
String body = (String) request.getAttribute("body");
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


	<div>

		<input type="hidden" name="boardId" value="<%=boardId%>" /> 
		<input type="hidden" name="memberId" value="<%=memberId%>" />
		<hr>
		번호 : 
		<%=article.id%><br> 
		작성자 :
		<%=article.extra__writer%><br> 
		제목 :
		<%=article.title%><br> 
		내용 :
		<%=article.body%><br> 
		작성일 :
		<%=article.regDate%><br>
		<hr>

	</div>

</body>
</html>