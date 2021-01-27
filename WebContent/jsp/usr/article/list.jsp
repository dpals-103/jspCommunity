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
		<h3>총 게시물 수 : ${totalCount}</h1>
	</div>

	<div>
		<a href="write?boardId=${param.boardId}&memberId=${sessionScope.loginedMemberId}">글쓰기</a>
		<hr>
	</div>
	<c:forEach items="${articles}" var="article">
	<div>

		번호 :
		${article.id}<br> 
		제목 :
		<a href="detail?boardId=${board.id}&id=${article.id}">${article.title}</a><br> 
		작성자 :
		${article.extra__writer}<br> 
		작성일 :
		${article.regDate}<br>
		<hr>

	</div>
	</c:forEach>
	<div>
	<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</div>
	
<%@ include file="../../part/foot.jspf" %>