<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jspCommunity.dto.Article"%>
<%@ page import="jspCommunity.dto.Board"%>

<%
	Article article = (Article) request.getAttribute("article");
	int boardId = (int)request.getAttribute("boardId"); 
	Board board = (Board)request.getAttribute("board"); 
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>

	<h1><%=board.category%>게시판</h1>
	<h1><%=article.id %>번글 상세보기</h1>

	<div>
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
	<div>
		<a href="list?boardId=<%=boardId%>">리스트로 돌아가기</a>
	</div>
	<div>
		<a href="modify?boardId=<%=boardId%>&id=<%=article.id%>">수정하기</a>
	</div>
	<form action="/jspCommunity/usr/article/doDelete?boardId=<%=boardId%>&id=<%=article.id%>" methods="POST" target="_blank">
		<input type="submit" value="삭제하기" />
	</form>
</body>
</html>