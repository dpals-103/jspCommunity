<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="게시물작성하기" />

<%@ include file="../../part/head.jspf" %>
<script >
	/*전송이 안됬을경우*/
	let DoWriteForm__submited = false; 
	
	function DoWriteForm__submit (form){

		/*중복전송 방지하기*/
		if (DoWriteForm__submited){
			alert('처리중입니다');
			return;
		}

		form.title.value = form.title.value.trim(); 
		if (form.title.value.length == 0){
			alert('제목을 입력해주세요')
			form.title.focus();

			return;
			 
			}

		const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
		const body = editor.getMarkdown().trim();
		 
		if (body.length == 0){
			alert('내용을 작성해주세요')
			editor.focus();

			return;
		}

		form.body.value = body; 

		form.submit();
		DoWriteForm__submited = true;
	}
	</script>
	
	<h1>${board.category} 글 작성하기</h1>
	<hr>
	
	<div>
	<form action="/jspCommunity/usr/article/doWrite" methods="POST" onsubmit="DoWriteForm__submit(this); return false;">
		<input type="hidden" name="boardId" value="${param.boardId}" />
		<input type="hidden" name="body"/>
		
		<hr>
		<input type="text" name="title" placeholder="제목을 입력하세요" maxlength="30"/>
		<hr>
		<script type="text/x-template"></script>
		<div class="toast-ui-editor" id="editor-1"></div>
		<hr>
		<input type="submit" value="전송"/>
		<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</form>
	</div>
	
<%@ include file="../../part/foot.jspf" %>