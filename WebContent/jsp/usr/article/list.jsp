<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>



<c:set var="pageTitle" value= "${board.category} 게시판 리스트"/>

<%@ include file="../../part/head.jspf" %>


	<div>
		<h1>${pageTitle}</h1>
	</div>
	<div>
		<h3>검색된 게시물 수 : ${totalCount}</h3>
	</div>
	<div>
	<script>
		let DoSearchForm__submited = false;
		function DoSearchForm__submit(form) {

			if (DoSearchForm__submited) {
				alert('처리중입니다');
				return;
			}

			form.searchKeyword.value = form.searchKeyword.value.trim();

			if (form.searchKeyword.value.length == 0) {
				alert('검색어를 입력해주세요');
				form.searchKeyword.focus();
				return;
			}

			form.submit();
			DoSearchForm__submited = true;
		}
	</script>
	<form action="" onsubmit="DoSearchForm__submit(this); return false;">

		<input type="hidden" name="boardId" value="${param.boardId}" /> 
		<select name="searchKeywordType" id="">
			<option value="title">제목</option>
			<option value="body">본문</option>
		</select>

		<script>
			const param__searchKeywordType = '${param.searchKeywordType}';

			if (param__searchKeywordType) {
				$('select[name="searchKeywordType"]').val(param__searchKeywordType);
			}
		</script>

		<input type="text" value="${param.searchKeyword}"name="searchKeyword" placeholder="검색어를 입력하세요"/>
			<input type="submit" value="검색" />
		</form>
		<hr />
	</div>

	<div>
		<a href="write?boardId=${param.boardId}&memberId=${sessionScope.loginedMemberId}">글쓰기</a>
		<hr>
	</div>
	<c:forEach items="${articles}" var="article">
	<div>

		번호 :
		${article.id}<br> 
		제목 :
		<a href="detail?boardId=${board.id}&id=${article.id}">${article.title}</a><br> 
		작성자 :
		${article.extra__writer}<br> 
		작성일 :
		${article.regDate}<br>
		<hr>

	</div>
	</c:forEach>
	
	<style>
		.red{
			color : red; 
		}
	</style>
	
	<div class ="con">
		<c:if test ="${needToShowPrevPageBox}">
			<c:set var="aUrl" value="?boardId=${param.boardId}&page=${prevPage}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
			<a href="${aUrl}">◀이전</a>
		</c:if>
		
		<c:forEach var="i" begin="${pageBoxStartPage}" end="${pageBoxEndPage}" step="1">
			<c:set var="aClass" value="${page == i ? 'red' : '' }"/>
			<c:set var="aUrl" value="?boardId=${param.boardId}&page=${i}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}"/>
			
			<a class="${aClass}" href="${aUrl}">${i}</a>
		</c:forEach>
		
		<c:if test ="${needToShowNextPageBox}">
			<c:set var="aUrl" value="?boardId=${param.boardId}&page=${nextPage}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}"  />
			<a href="${aUrl}">다음▶</a>
		</c:if>
	</div>
	
	<div>
	<button type="button" onclick="history.back();"/>뒤로가기</button>	
	</div>
	
<%@ include file="../../part/foot.jspf" %>