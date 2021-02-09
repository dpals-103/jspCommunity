<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<c:set var="pageTitle" value="아이디 찾기" />

<%@ include file="../../part/head.jspf" %>

	
	<h1>${pageTitle}</h1>
	<hr>
	
	<div>
	
	<!-- 회원가입 폼 체크 -->
	<script >
	/*전송이 안됬을경우*/
	let DoFindLoginIdForm__submited = false; 
	
	function DoFindLoginIdForm__submit (form){

		/*중복전송 방지하기*/
		if (DoFindLoginIdForm__submited){
			alert('처리중입니다');
			return;
		}

		form.name.value =  form.name.value.trim(); 
		if (form.name.value.length == 0){
			alert('이름을 입력해주세요')
			form.name.focus();

			return;
			 
			}

		form.email.value =  form.email.value.trim(); 
		if (form.email.value.length == 0){
			alert('가입 시 입력한 이메일을 입력해주세요')
			form.email.focus();

			return;
			 
		}


		form.loginPwReal.value = sha256(form.loginPw.value);
		form.loginPw.value = ""; 

		form.submit();
		DoFindLoginIdForm__submited = true;
	}
	</script>
	
	
	
	<form action="/jspCommunity/usr/member/doFindLoginId" methods="POST" onsubmit="DoFindLoginIdForm__submit(this); return false;">
	
	<input type="hidden" name="loginPwReal" />
	
		<div>이름</div>
	    <input type="text" name="name" placeholder="이름을 입력하세요" maxlength="50"/>
		<hr>
		<div>이메일</div>
		<input type="email" name="email" placeholder="회원의 이메일을 입력하세요" maxlength="100"/>
		<hr>
		
		<input type="submit" value="찾기"/>
		<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</form>
	</div>
	
<%@ include file="../../part/foot.jspf" %>