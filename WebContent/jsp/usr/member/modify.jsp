<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="pageTitle" value="회원정보 수정하기" />

<%@ include file="../../part/head.jspf"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<h1>${pageTitle}</h1>
<hr>

<div>


	<script>
		let DoModifyForm__submited = false;

		/*회원가입 폼 체크*/
		function DoModifyForm__submit(form) {
			if (DoModifyForm__submited) {
				alert('처리중입니다.');
				return;
			}

			/*비밀번호 변경을 시도할 때에만*/
			form.loginPw.value = form.loginPw.value.trim();
	
		if ( form.loginPw.value.length > 0 ) {
			form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		
			if ( form.loginPwConfirm.value.length == 0 ) {
				alert('변경된 비밀번호를 입력해주세요.');
				form.loginPwConfirm.focus();
				
				return;
			}
			
			if ( form.loginPw.value != form.loginPwConfirm.value ) {
				alert('로그인 비밀번호가 일치하지 않습니다.');
				form.loginPwConfirm.focus();
				
				return;
			}
		}
		
			form.name.value = form.name.value.trim();

			if (form.name.value.length == 0) {
				alert('이름을 입력해주세요.');
				form.name.focus();

				return;
			}

			form.nickName.value = form.nickName.value.trim();

			if (form.nickName.value.length == 0) {
				alert('별명을 입력해주세요.');
				form.nickName.focus();

				return;
			}

			form.email.value = form.email.value.trim();

			if (form.email.value.length == 0) {
				alert('이메일을 입력해주세요.');
				form.email.focus();

				return;
			}

			form.cellPhone.value = form.cellPhone.value.trim();

			if (form.cellPhone.value.length == 0) {
				alert('전화번호를 입력해주세요.');
				form.cellPhone.focus();

				return;
			}

			if (form.loginPw.value.length > 0) {
				
				form.loginPwReal.value = sha256(form.loginPw.value);
				form.loginPw.value = "";
				form.loginPwConfirm.value = "";
			}

			form.submit();
			DoModifyForm__submited = true;
			alert(form.loginPwReal.value);
		}
	</script>


	<form action="/jspCommunity/usr/member/doModify" methods="POST"
		onsubmit="DoModifyForm__submit(this); return false;">
		<input type="hidden" name="loginPwReal" />

		<div>아이디</div>
		<div>${loginedMember.loginId}</div>
		<hr>
		<div>비밀번호</div>
		<input type="password" name="loginPw" placeholder="비밀번호를 입력하세요"
			maxlength="100" />
		<hr>
		<div>비밀번호 확인</div>
		<input type="password" name="loginPwConfirm"
			placeholder="비밀번호를 한번 더 입력해주세요" maxlength="100" />
		<hr>
		<div>이름</div>
		<div>${loginedMember.name}</div>
		<hr>
		<div>활동명</div>
		<input type="text" name="nickName" placeholder="활동명(별명)을 입력하세요"
			maxlength="30" value="${loginedMember.nickName}" />
		<hr>
		<div>전화번호</div>
		<input type="tel" name="cellPhone" placeholder="연락처를 입력하세요"
			maxlength="100" value="${loginedMember.cellPhone}" />
		<hr>
		<div>이메일</div>
		<input type="email" name="email" placeholder="이메일을 입력하세요"
			maxlength="100" value="${loginedMember.email}" />
		<hr>
		<input type="submit" value="수정" />
		<button type="button" onclick="history.back();" />
		뒤로가기
		</button>
	</form>
</div>

<%@ include file="../../part/foot.jspf"%>