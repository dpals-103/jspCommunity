<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="pageTitle" value="회원가입" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<%@ include file="../../part/head.jspf"%>

<style>
section {
	flex-grow: 1;
}

.signup-box>.con {
	width: 300px;
	flex-direction: column;
	align-items: center;
}

.signup-box>.con>.signup-title {
	margin-top: 100px;
}

.signup-box>.con>.signup-title>span {
	font-size: 5vh;
	white-space: nowrap;
	color: #7590a1;
	font-weight: 700;
}

.signup-box>.con>.signup-title>span+span {
	margin-left: 1rem;
}

.signup-box>.con>.signup-form {
	margin-top: 1rem;
}

.signup-box>.con>.signup-form>div {
	color: #ceccc0;
	font-size: 2vh;
	margin-bottom: 1rem;
	white-space: nowrap;
}

.signup-box>.con>.signup-form  input {
	padding: 0.2rem;
	width: 50vw;
}

.signup-form .finish-btn:hover {
	cursor: pointer;
}

.signup-box button, .signup-form .finish-btn {
	border: none;
	padding: 0.2rem;
	background: #7590a1;
	color: white;
}

@media ( max-width :800px) {
	.signup-form input, .signup-form button {
		border-radius: 4px;
	}
	.btnDupCheck__mobile {
		display: block;
		width : 50vw;
		margin-top: 0.5rem;
	}
}
</style>


<section>
	<div class="signup-box con-min-width">
		<div class="con flex">

			<div class="signup-title flex">
				<span>
					<i class="fas fa-compact-disc"></i>
				</span>
				<span>Sign Up</span>
			</div>

			<script>
	let DoJoinForm__submited = false;

	/*인증받은 아이디*/ 
	let DoJoinForm__checkedLoginId = ""; 
	
	/*로그인 아이디 중복체크*/
	function CheckDupId(el){

		const from = $(el).closest('form').get(0);  
		const loginId = from.loginId.value;

		$.get(
			"getLoginIdDup",
			{
				loginId
				/* => loginId : loginId */
			},
			function(data){
				if (data.msg){
					alert(data.msg); 
				}
				if (data.success){
					DoJoinForm__checkedLoginId = data.body.loginId; 
				}
			},
			"json"
	   ); 

	}
	

		/*회원가입 폼 체크*/
	function DoJoinForm__submit(form) {
		if ( DoJoinForm__submited ) {
			alert('처리중입니다.');
			return;
		}
	
		form.loginId.value = form.loginId.value.trim();
	
		if ( form.loginId.value.length == 0 ) {
			alert('로그인 아이디를 입력해주세요.');
			form.loginId.focus();
			
			return;
		}
		

		if(form.loginId.value != DoJoinForm__checkedLoginId){
			alert('아이디 중복검사를 해주세요'); 
			form.btnDupCheck.focus(); 

			return false; 
		}

		
		form.loginPw.value = form.loginPw.value.trim();
		if ( form.loginPw.value.length == 0 ) {
			alert('로그인 비밀번호를 입력해주세요.');
			form.loginPw.focus();
			
			return;
		}
		
		form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		if ( form.loginPwConfirm.value.length == 0 ) {
			alert('로그인 비밀번호 확인을 입력해주세요.');
			form.loginPwConfirm.focus();
			
			return;
		}
		
		if ( form.loginPw.value != form.loginPwConfirm.value ) {
			alert('로그인 비밀번호가 일치하지 않습니다.');
			form.loginPwConfirm.focus();
			
			return;
		}

		
		
		form.name.value = form.name.value.trim();
		if ( form.name.value.length == 0 ) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			
			return;
		}
		
		form.nickName.value = form.nickName.value.trim();
	
		if ( form.nickName.value.length == 0 ) {
			alert('별명을 입력해주세요.');
			form.nickName.focus();
			
			return;
		}
		
		form.email.value = form.email.value.trim();
	
		if ( form.email.value.length == 0 ) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			
			return;
		}
		
		form.cellPhone.value = form.cellPhone.value.trim();
	
		if ( form.cellPhone.value.length == 0 ) {
			alert('전화번호를 입력해주세요.');
			form.cellPhone.focus();
			
			return;
		}

		form.loginPwReal.value = sha256(form.loginPw.value); 
		form.loginPw.value =""; 
		form.loginPwConfirm.value =""; 

		
		form.submit();
		DoJoinForm__submited = true;
	}
	</script>
			<form action="/jspCommunity/usr/member/doJoin" methods="POST"
				onsubmit="DoJoinForm__submit(this); return false;"
				class="signup-form">

				<input type="hidden" name="loginPwReal" />

				<div id="loginId">
					<div>아이디 Id</div>
					<input type="text" name="loginId" placeholder="아이디를 입력하세요"
						maxlength="50">
					<button type="button" onclick="CheckDupId(this);"
						name="btnDupCheck" class="visible-md-up">중복체크</button>
					<button type="button" onclick="CheckDupId(this);"
						name="btnDupCheck" class="btnDupCheck__mobile visible-sm-down">중복체크</button>
				</div>
				<div id="loginPw">
					<div>비밀번호 Password</div>
					<input type="password" name="loginPw" placeholder="비밀번호를 입력하세요"
						maxlength="100">
				</div>
				<div id="loginPwConfirm">
					<div>비밀번호 확인 Password Confirm</div>
					<input type="password" name="loginPwConfirm"
						placeholder="비밀번호를 확인해주세요" maxlength="100">
				</div>
				<div id="name">
					<div>이름 Name</div>
					<input type="text" name="name" placeholder="이름을 입력해주세요"
						maxlength="30">
				</div>
				<div id="nickName">
					<div>활동명 nickName</div>
					<input type="text" name="nickName" placeholder="별명을 입력해주세요"
						maxlength="100">
				</div>
				<div id="email">
					<div>E-mail</div>
					<input type="email" name="email" placeholder="이메일을 입력해주세요"
						maxlength="100">
				</div>
				<div id="cellPhone">
					<div>연락처 cellPhoneNo</div>
					<input type="tel" name="cellPhone" placeholder="연락처를 입력해주세요"
						maxlength="100">
				</div>

				<input class="finish-btn visible-md-up" type="submit" value="완료">
				<input class="finish-btn finish-btn__mobile visible-sm-down"
					type="submit" value="완료">
			</form>
		</div>
	</div>
</section>
<!-- 섹션 끝-->
<%@ include file="../../part/foot.jspf"%>