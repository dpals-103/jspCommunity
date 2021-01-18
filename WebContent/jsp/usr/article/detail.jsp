<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="게시물 상세보기" />

<%@ include file="../../part/head.jspf" %>


	<h1>${board.categoty} 게시판</h1>
	<h1>${article.id}번글 상세보기</h1>

	<div>
		번호 :
		${article.id}<br> 
		작성자 :
		${article.extra__writer}<br>
		제목 :
		${article.title}<br> 
		내용 :
		${article.body}<br> 
		작성일 :
		${article.regDate}<br>
		<hr>
	</div>
	<div>
		<a href="list?boardId=${article.boardId}">리스트로 돌아가기</a>
	</div>
	<div>
		<a href="modify?boardId=${article.boardId}&id=${article.id}">수정하기</a>
	</div>
	<form action="/jspCommunity/usr/article/doDelete?boardId=${article.boardId}&id=${article.id}" methods="POST" target="_blank">
		<input type="submit" value="삭제하기" />
	</form>
	
<%@ include file="../../part/foot.jspf" %>