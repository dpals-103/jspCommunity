<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
	
<c:set var="pageTitle" value="게시물 수정하기" />

<%@ include file="../../part/head.jspf" %>

<script>
	/*전송이 안됬을경우*/
	let DoModifyForm__submited = false; 
	
	function DoModifyForm__submit (form){

		/*중복전송 방지하기*/
		if (DoModifyForm__submited){
			alert('처리중입니다');
			return;
		}

		const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
		const body = editor.getMarkdown().trim();

		form.body.value = body; 

		form.submit();
		DoModifyForm__submited = true;
	}
	</script>

	<h1>글 수정하기</h1>
	<hr>
	
	<div>
	<form action="/jspCommunity/usr/article/doModify" methods="POST" onsubmit="DoModifyForm__submit(this); return false;">
		<input type="hidden" name="boardId" value="${param.boardId}" />
		<input type="hidden" name="id" value="${param.id}" />
		<input type="hidden" name="memberId" value="${param.memberId}" />
		<input type="hidden" name="body" />
		<hr>
		<input type="text" name="title" value="${article.title}" maxlength="30"/>
		<hr>
		<script type="text/x-template">${article.body}</script>
		<div class="toast-ui-editor" id="editor-1"></div>
		<hr>
		<input type="submit" value="수정"/>
		<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</form>
	</div>
	
<%@ include file="../../part/foot.jspf" %>