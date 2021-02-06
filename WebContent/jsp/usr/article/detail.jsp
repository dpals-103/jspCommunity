<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="게시물 상세보기" />

<%@ include file="../../part/head.jspf" %>

<style>
	*{
	color : #ceccc0;
	}
</style>

	<h1>${board.category} 게시판</h1>
	<h1>${article.id}번글 상세보기</h1>
	
	<a href="list?boardId=${article.boardId}">리스트로 돌아가기</a>
	<c:if test="${sessionScope.loginedMemberId == article.memberId }">
		<a href="modify?boardId=${param.boardId}&id=${param.id}&memberId=${article.memberId}">수정하기</a>
		<a href="/jspCommunity/usr/article/doDelete?boardId=${param.boardId}&id=${param.id}&memberId=${article.memberId}">삭제하기</a>
	</c:if>
	

	<div>
		번호 :
		${article.id}<br> 
		작성자 :
		${article.extra__writer}<br>
		제목 :
		${article.title}<br> 
		<script type="text/x-template"> ${article.body} </script>
		<div class="toast-ui-viewer" ></div>
		<br> 
		작성일 :
		${article.regDate}<br>
		<hr>
	</div>
	
	
	<div>		
		<a href="../like/doLike?boardId=${board.id}&memberId=${loginedMemberId}&id=${article.id}">
		
		<c:if test="${liked == false}">
		<i class="far fa-heart"></i>${like}
		</c:if>
		
		<c:if test="${liked}">
		<i class="fas fa-heart"></i>${like}
		</c:if>
		
		</a>
		
		<span></span>
		
	
		<a href="../like/doDislike?boardId=${board.id}&memberId=${loginedMemberId}&id=${article.id}">
		
		<c:if test="${disliked == false}">
		<i class="far fa-thumbs-down"></i>${dislike}
		</c:if>
		
		<c:if test="${disliked}">
		<i class="fas fa-thumbs-down"></i>${dislike}
		</c:if>
		
		</a>

		
	</div>
	
	<script>
		let DoReplyForm__submited = false; 

		function DoReplyForm__submit (form){
			if (DoReplyForm_submited){
				alert('처리중입니다'); 
				return; 		
			}

			if(form.body.value.length == 0){
				alert('내용을 입력해주세요'); 
				form.body.focus();

				return; 
			}

			form.submit();
			DoReplyForm__submited = true;

		}
	</script>
	
	
	
	<form action="../reply/doReply" methods="POST" onsubmit="DoReplyForm__submit(this); return false;">
		<input type="hidden" name="id" value="${param.id}"/>
		<input type="hidden" name="boardId" value="${param.boardId}"/>
		
		<h5>${loginedMember.nickName}</h5>
		<textarea name="body" id="" cols="30" rows="10"></textarea>
		<input type="submit" value="댓글"/>
	</form>
	
	<div>
		<c:forEach items="${replies}" var="reply" >
			<hr/>
			<div>${reply.extra__nickName}</div>
			<div>${reply.body}</div>
			
			<c:if test="${sessionScope.loginedMemberId == reply.memberId }">
				<a href="../reply/modify?id=${param.id}&boardId=${param.boardId}&replyId=${reply.id}&&memberId=${reply.memberId}">수정하기</a>
				<a href="../reply/doDelete?id=${param.id}&boardId=${param.boardId}&replyId=${reply.id}&&memberId=${reply.memberId}">삭제하기</a>
			</c:if>
			<hr/>
		</c:forEach>	
	</div>
	
	

	
<%@ include file="../../part/foot.jspf" %>