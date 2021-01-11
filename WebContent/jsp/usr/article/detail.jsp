<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jspCommunity.dto.Article"%>

<%
	Article article = (Article) request.getAttribute("article");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
</head>
<body>

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

</body>
</html>