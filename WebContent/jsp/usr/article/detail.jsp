<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jspCommunity.dto.Article"%>
<%@ page import="jspCommunity.dto.Board"%>

<%
	Article article = (Article) request.getAttribute("article");
	int boardId = (int)request.getAttribute("boardId"); 
	Board board = (Board)request.getAttribute("board"); 
	String pageTitle = "게시물 상세보기";
%>

<%@ include file="../../part/head.jspf" %>

	<h1><%=board.getCategory()%>게시판</h1>
	<h1><%=article.getId() %>번글 상세보기</h1>

	<div>
		번호 :
		<%=article.getId()%><br> 
		작성자 :
		<%=article.getExtra__writer()%><br>
		제목 :
		<%=article.getTitle()%><br> 
		내용 :
		<%=article.getBody()%><br> 
		작성일 :
		<%=article.getRegDate()%><br>
		<hr>
	</div>
	<div>
		<a href="list?boardId=<%=boardId%>">리스트로 돌아가기</a>
	</div>
	<div>
		<a href="modify?boardId=<%=boardId%>&id=<%=article.getId()%>">수정하기</a>
	</div>
	<form action="/jspCommunity/usr/article/doDelete?boardId=<%=boardId%>&id=<%=article.getId()%>" methods="POST" target="_blank">
		<input type="submit" value="삭제하기" />
	</form>
	
<%@ include file="../../part/foot.jspf" %>