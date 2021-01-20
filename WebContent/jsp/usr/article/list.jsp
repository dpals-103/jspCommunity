<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>



<c:set var="pageTitle" value= "${board.category} 게시판 리스트"/>

<%@ include file="../../part/head.jspf" %>


	<div>
		<h1>${pageTitle}</h1>
	</div>

	<div>
		<c:if test="${session.loginedMemberId > 0}">
		<a href="write?boardId=${param.boardId}&memberId=${session.loginedMemberId}">글쓰기</a>
		</c:if>
	</div>
	<c:forEach items="${articles}" var="article">
	<div>

		게시판 :
		${article.extra__category}<br> 
		번호 :
		${article.id}<br> 
		작성자 :
		${article.extra__writer}<br> 
		제목 :
		${article.title}<br> 
		작성일 :
		${article.regDate}<br>
		<hr>

	</div>
	</c:forEach>
	<div>
	<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</div>
	
<%@ include file="../../part/foot.jspf" %>