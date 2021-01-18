<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import= "java.util.List"%>
<%@ page import= "java.util.Map"%>


<c:set var="pageTitle" value="회원리스트"/>

<%@ include file="../../part/head.jspf" %>

	<h1>회원리스트</h1>
	
	<c:forEach items="${members}" var="member">
	
	<div>
		번호 : ${member.id}
		<br>
		이름 : ${member.name}
	</div>
	<hr>
	</c:forEach>
	
<%@ include file="../../part/foot.jspf" %>