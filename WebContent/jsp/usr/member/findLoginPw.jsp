<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<c:set var="pageTitle" value="비밀번호 찾기" />

<%@ include file="../../part/head.jspf" %>

	
	<h1>${pageTitle}</h1>
	<hr>
	
	<div>
	
	<!-- 회원가입 폼 체크 -->
	<script >
	/*전송이 안됬을경우*/
	let DoFindLoginPwForm__submited = false; 
	
	function DoFindLoginPwForm__submit (form){

		/*중복전송 방지하기*/
		if (DoFindLoginPwForm__submited){
			alert('처리중입니다');
			return;
		}

		form.loginId.value =  form.loginId.value.trim(); 
		if (form.loginId.value.length == 0){
			alert('아이디을 입력해주세요')
			form.loginId.focus();

			return;
			 
			}

		form.email.value =  form.email.value.trim(); 
		if (form.email.value.length == 0){
			alert('이메일을 입력해주세요')
			form.email.focus();

			return;
			 
		}


		form.loginPwReal.value = sha256(form.loginPw.value);
		form.loginPw.value = ""; 

		form.submit();
		DoFindLoginIdForm__submited = true;
	}
	</script>
	
	
	
	<form action="/jspCommunity/usr/member/doFindLoginPw" methods="POST" onsubmit="DoFindLoginPwForm__submit(this); return false;">
	
	<input type="hidden" name="loginPwReal" />
	
		<div>아이디</div>
	    <input type="text" name="loginId" placeholder="아이디를 입력하세요" maxlength="50"/>
		<hr>
		<div>이메일</div>
		<input type="email" name="email" placeholder="회원의 이메일을 입력하세요" maxlength="100"/>
		<hr>
		
		<input type="submit" value="찾기"/>
		<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</form>
	</div>
	
<%@ include file="../../part/foot.jspf" %>