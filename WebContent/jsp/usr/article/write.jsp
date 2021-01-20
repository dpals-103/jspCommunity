<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<c:set var="pageTitle" value="게시물작성하기" />

<%@ include file="../../part/head.jspf" %>

	
	<h1>${board.category} 글 작성하기</h1>
	<hr>
	
	<div>
	<form action="/jspCommunity/usr/article/doWrite" methods="POST">
		<input type="hidden" name="boardId" value="${param.boardId}" />
		<input type="hidden" name="memberId" value="${param.memberId}" />
		<hr>
		<input type="text" name="title" placeholder="제목을 입력하세요" maxlength="30"/>
		<hr>
		<textarea name="body" placeholder="내용을 입력해주세요" maxlength="1500"></textarea>
		<hr>
		<input type="submit" value="전송"/>
		<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</form>
	</div>
	
<%@ include file="../../part/foot.jspf" %>