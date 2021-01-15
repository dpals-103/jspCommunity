<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="jspCommunity.dto.Article"%>
<%@ page import="jspCommunity.dto.Board"%>

<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
	int boardId = (int) request.getAttribute("boardId");
	Board board = (Board) request.getAttribute("board");
	String pageTitle = board.getCategory() + "게시판 리스트";
%>

<%@ include file="../../part/head.jspf" %>


	<div>
		<h1><%=pageTitle%>
		</h1>
	</div>

	<div>
		<a href="write?boardId=<%=boardId%>">글쓰기</a>
	</div>
	<%
		for (Article article : articles) {
	%>
	<div>

		게시판 :
		<%=article.getExtra__category()%><br> 
		번호 :
		<%=article.getId()%><br> 
		작성자 :
		<%=article.getExtra__writer()%><br> 
		제목 :
		<%=article.getTitle()%><br> 
		작성일 :
		<%=article.getRegDate()%><br>
		<hr>

	</div>
	<%
		}
	%>
	
<%@ include file="../../part/foot.jspf" %>