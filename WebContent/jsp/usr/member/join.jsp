<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<c:set var="pageTitle" value="회원가입" />

<%@ include file="../../part/head.jspf" %>

	
	<h1>회원가입</h1>
	<hr>
	
	<div>
		<form action="/jspCommunity/usr/member/doJoin" methods="POST" target="_blank">
	    <input type="text" name="loginId" placeholder="아이디를 입력하세요" maxlength="30"/>
		<hr>
		<input type="text" name="loginPw" placeholder="비밀번호를 입력하세요" maxlength="100"/>
		<hr>
		<input type="text" name="name" placeholder="이름을 입력하세요" maxlength="30"/>
		<hr>
		<input type="text" name="nickName" placeholder="활동명(별명)을 입력하세요" maxlength="30"/>
		<hr>
		<input type="email" name="email" placeholder="이메일을 입력하세요" maxlength="100"/>
		<hr>
		<input type="submit" value="전송"/>
		<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</form>
	</div>
	
<%@ include file="../../part/foot.jspf" %>