<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Main" />

<%@ include file="../../part/head.jspf"%>


<div>
	<h1>${pageTitle}</h1>
</div>

<c:forEach items="${boards}" var="board">
	<div>
		<a href="../article/list?boardId=${board.id}">${board.category}</a>
	</div>
</c:forEach>


<%@ include file="../../part/foot.jspf"%>