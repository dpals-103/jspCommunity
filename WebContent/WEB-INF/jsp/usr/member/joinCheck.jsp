<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<c:set var="pageTitle" value="가입완료" />

<%@ include file="../../part/head.jspf" %>

	
	<h1>가입성공!</h1>
	<hr>
	
	<h3>
	${param.loginId}님 환영합니다!
	</h3>
	<div>
	${param.memberId}번째 회원이 되셨습니다!
	</div>
	
<%@ include file="../../part/foot.jspf" %>