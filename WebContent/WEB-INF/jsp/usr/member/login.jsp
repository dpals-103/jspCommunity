<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<c:set var="pageTitle" value="로그인" />

<%@ include file="../../part/head.jspf"%>


<style>
section{
  flex-grow : 1; 
}

.signin-box > .con{
  width : 300px;
  flex-direction: column;
  align-items : center;
}

.signin-box > .con > .signin-title{
  margin-top : 100px;
}

.signin-box > .con > .signin-title > span{
  font-size : 5vh;
  white-space : nowrap; 
  color : #7590a1;
  font-weight : 700;
}

.signin-box > .con > .signin-title > span + span{
  margin-left : 1rem;
}

.signin-box > .con > .signin-form{
  margin-top : 1rem;
}

.signin-box > .con > .signin-form > div{
  color : #ceccc0;
  font-size : 2vh;
  margin-bottom : 1rem;
  white-space : nowrap; 
}

.signin-box > .con > .signin-form  input{
  padding : 0.2rem; 
  width : 50vw;
}

.signin-form .finish-btn:hover {
  cursor : pointer;
}

.signin-box button,
.signin-form .finish-btn{
  border : none;
  padding : 0.2rem; 
  background : #7590a1;
  color : white;
}

.find-account-btn{
  margin : 0.5rem;
}

.find-account-btn span {
  color : #ceccc0; 
  font-size : 2vh;
}

.find-account-btn > a:hover > span{
  color : #7590a1;
}

.find-account-btn > a:active > span{
  color : #7590a1;
}

.find-account-btn > span:nth-child(2){
  margin : 0 0.5rem;
}

@media(max-width:800px){
  .signin-form input,
  .signin-form button{
    display : block;
    border-radius : 4px;
    
  }
  .btnDupCheck__mobile{
    display : block;
    width : 100%;
    margin-top : 0.5rem;
  }
}
</style>

<!-- 섹션 시작-->
<section>
	<div class="signin-box con-min-width">
		<div class="con flex">
			<div class="signin-title flex">
				<span><i class="fas fa-compact-disc"></i></span> <span>Sign
					In</span>
			</div>


			<!-- 회원가입 폼 체크 -->
			<script>
				/*전송이 안됬을경우*/
				let DoLoginForm__submited = false;

				function DoLoginForm__submit(form) {

					/*중복전송 방지하기*/
					if (DoLoginForm__submited) {
						alert('처리중입니다');
						return;
					}

					form.loginId.value = form.loginId.value.trim();
					if (form.loginId.value.length == 0) {
						alert('아이디를 입력해주세요')
						form.loginId.focus();

						return;

					}

					form.loginPw.value = form.loginPw.value.trim();
					if (form.loginPw.value.length == 0) {
						alert('비밀번호를 입력해주세요')
						form.loginPw.focus();

						return;

					}

					form.loginPwReal.value = sha256(form.loginPw.value);
					form.loginPw.value = "";

					form.submit();
					DoLoginForm__submited = true;
				}
			</script>
			<form action="doLogin" methods="POST"
				onsubmit="DoLoginForm__submit(this); return false"
				class="signin-form">
				
				<input type="hidden" name="loginPwReal"/>
				<input type="hidden" name="afterLoginUrl" value="${param.afterLoginUrl}"/>
				
				
				<div id="loginId">
					<div>아이디 Id</div>
					<input type="text" name="loginId" placeholder="아이디를 입력하세요"
						maxlength="50">
				</div>
				<div id="loginPw">
					<div>비밀번호 Password</div>
					<input type="password" name="loginPw" placeholder="비밀번호를 입력하세요"
						maxlength="100">
				</div>
				<input class="finish-btn visible-md-up" type="submit" value="완료">
				<input class="finish-btn finish-btn__mobile visible-sm-down"
					type="submit" value="완료">
			</form>
			<div class="find-account-btn">
				<a href="../member/findLoginId"><span>아이디 찾기</span></a>
				<span>/</span> 
				<a href="../member/findLoginPw"><span>비밀번호 찾기</span></a>

			</div>

		</div>
	</div>
</section>
<!-- 섹션 끝-->



<%@ include file="../../part/foot.jspf"%>