<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="회원가입" />

<%@ include file="../../part/head.jspf"%>


<h1>회원가입</h1>
<hr>

<div>

 
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
				if (data.resultCode.substr(0,2) == "S-"){
					alert(data.msg);
					DoJoinForm__checkedLoginId = data.loginId; 
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
		
		form.submit();
		DoJoinForm__submited = true;
	}
	</script>


	<form action="/jspCommunity/usr/member/doJoin" methods="POST"
		onsubmit="DoJoinForm__submit(this); return false;">


		<div>아이디</div>
		<input type="text" name="loginId" id="loginId" placeholder="아이디를 입력하세요"
			maxlength="50" />
		<button type="button" onclick="CheckDupId(this);" name="btnDupCheck">중복체크</button>
		<hr>
		<div>비밀번호</div>
		<input type="text" name="loginPw" placeholder="비밀번호를 입력하세요"
			maxlength="100" />
		<hr>
		<div>비밀번호 확인</div>
		<input type="text" name="loginPwConfirm"
			placeholder="비밀번호를 한번 더 입력해주세요" maxlength="100" />
		<hr>
		<div>이름</div>
		<input type="text" name="name" placeholder="이름을 입력하세요" maxlength="30" />
		<hr>
		<div>활동명</div>
		<input type="text" name="nickName" placeholder="활동명(별명)을 입력하세요"
			maxlength="30" />
		<hr>
		<div>전화번호</div>
		<input type="number" name="cellPhone" placeholder="연락처를 입력하세요"
			maxlength="100" />
		<hr>
		<div>이메일</div>
		<input type="email" name="email" placeholder="이메일을 입력하세요"
			maxlength="100" />
		<hr>
		<input type="submit" value="전송" />
		<button type="button" onclick="history.back();" />
		뒤로가기
		</button>
	</form>
</div>

<%@ include file="../../part/foot.jspf"%>