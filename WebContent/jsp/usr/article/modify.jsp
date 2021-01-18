<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
	
<c:set var="pageTitle" value="게시물 수정하기" />

<%@ include file="../../part/head.jspf" %>

	<h1>글 수정하기</h1>
	<hr>
	
	<div>
	<form action="/jspCommunity/usr/article/doModify" methods="POST" target="_blank">
		<input type="hidden" name="boardId" value="${param.boardId}" />
		<input type="hidden" name="id" value="${param.id}" />
		<input type="hidden" name="memberId" value="${param.memberId}" />
		<hr>
		<input type="text" name="title" value="${article.title}" maxlength="30"/>
		<hr>
		<textarea name="body" maxlength="1500">${article.body}</textarea>
		<hr>
		<input type="submit" value="전송"/>
		<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</form>
	</div>
	
<%@ include file="../../part/foot.jspf" %>