<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>

<style>
/*섹션*/
section {
	flex-grow: 1;
}

.list-box>.con {
	margin-top: 130px;
	flex-direction: column;
	align-items: center;
}

.list-box>.con>.list-title {
	font-size: 5vh;
	white-space: nowrap;
	color: #9f898c;
	font-weight: 700;
	margin-bottom: 2rem;
}

.list-search {
	width: 100%;
	justify-content: space-between;
	margin-bottom: 1rem;
	white-space: nowrap;
	align-items : center;
}

.list-search>span {
	color: #ceccc0;
	white-space: nowrap;
	margin-right: 2.5rem;
}

.list-search #search-box{
	width: 25vw;
	height : 3vh;
	vertical-align: bottom;
}
.list-search #search-btn,
.list-search select{
	height : 3vh;
	vertical-align: bottom;
}

.list-box th, .list-box td span, .list-box td a {
	color: #ceccc0;
	font-size: 2vh;
}

.list-box>.con>table {
	border-collapse: collapse;
	table-layout: fixed;
	width: 100%;
	border-top: solid 3px #ceccc0;
}

.list-box>.con>table tr {
	border-bottom: solid 0.5px #ceccc0;
}

.list-box>.con>table>thead th, .list-box>.con>table>tbody td {
	padding: 0.8rem;
}

.list-box>.con>table>tbody td {
	text-align: center;
}

.list-box>.con>table>tbody td .list-title {
	white-space: nowrap;
	display: block;
	text-overflow: ellipsis;
	overflow: hidden;
}

.list-box>.con>table>tbody .list-title:hover {
	text-decoration: underline;
	color: #9f898c;
}

.list-box>.con>.list-paging {
	margin-top: 2rem;
}

.list-box>.con>.list-paging>a {
	padding: 0 0.5rem;
}

.list-box>.con>.list-paging span {
	color: #ceccc0;
}

.list-box>.con>.list-paging>a:hover>span {
	color: #9f898c;
}

.list-box>.con>.write-btn {
	background-color: #ceccc0;
	color: #282828;
	border-radius: 4px;
	margin-top: 1rem;
}

.list-box>.con>.write-btn > a:hover{
	background-color : #9f898c;
	color : white;
}

.list-box>.con>.write-btn > a:active{
	background-color : #9f898c;
	color : white;
}

.list-box>.con>.write-btn>a {
	padding: 0.2rem;
}

/*리스트 박스 모바일 버전*/
@media ( max-width :800px) {
	.list-box>.con>table>tbody>tr>td:not(.visible-sm-down) {
		display: none;
	}
	.list-box>.con>table>tbody>tr {
		width: 100%;
	}
	.list-box>.con>table>colgroup>col {
		width: auto;
	}
	.list-box>.con>table>colgroup>col:first-child {
		width: 100% !important;
	}
	.list-title-mobile {
		font-weight: 700;
	}
	#split {
		margin: 0 0.6rem;
	}
	.list-search {
		display: block;
	}
	.list-search form {
		margin-top: 1rem;
	}
}
</style>
<c:set var="pageTitle" value="${board.category} 게시판 리스트" />

<%@ include file="../../part/head.jspf"%>

<section>
	<div class="list-box con-min-width">

		<div class="con flex">
			<div class="list-title flex">
				<span><i class="fas fa-pencil-alt"></i></span> <span>${board.category}</span>
			</div>

			<div class="list-search flex">
				<span>검색된 게시물 수 : ${totalCount} </span>


				<script>
					let DoSearchForm__submited = false;
					function DoSearchForm__submit(form) {

						if (DoSearchForm__submited) {
							alert('처리중입니다');
							return;
						}

						form.searchKeyword.value = form.searchKeyword.value
								.trim();

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

					<input type="hidden" name="boardId" value="${param.boardId}" /> <select
						name="searchKeywordType">
						<option value="title">제목</option>
						<option value="body">본문</option>
					</select>


					<script>
						const param__searchKeywordType = '${param.searchKeywordType}';

						if (param__searchKeywordType) {
							$('select[name="searchKeywordType"]').val(
									param__searchKeywordType);
						}
					</script>

					<input type="text" value="${param.searchKeyword}"
						name="searchKeyword" placeholder="검색어를 입력하세요" id="search-box" />
					<input id="search-btn" type="submit" value="검색" />
				</form>
			</div>

			<table>
				<colgroup>
					<col width="400">
					<col width="100">
					<col width="100">
					<col width="200">
				</colgroup>
				<thead class="visible-md-up">

					<tr>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>날짜</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${articles}" var="article">
						<tr>
							<td><a href="detail?boardId=${board.id}&id=${article.id}">${article.title}</a></td>
							<td><span class="list-writer">${article.extra__writer}</span></td>
							<td><span class="list-count">${article.count}</span></td>
							<td><span class="list-regDate">${article.regDate}</span></td>

							<!-- 모바일 버전 -->
							<td class="visible-sm-down">
								<div class="flex">
									<a href="detail?boardId=${board.id}&id=${article.id}"
										class="list-title list-title-mobile">${article.title}</a>
								</div>
								<div class="flex">
									<span>${article.extra__writer}</span> <span id="split">|</span>
									<span>${article.count}</span> <span id="split">|</span> <span>${article.regDate}</span>
								</div>
							</td>
					</c:forEach>
				</tbody>

				<!--모바일 버전 끝-->
				</tr>
				</tbody>
			</table>
			<div class="flex write-btn">
				<a
					href="write?boardId=${param.boardId}&memberId=${sessionScope.loginedMemberId}">글쓰기</a>
			</div>
			<div class="list-paging">

				<c:if test="${needToShowPrevPageBox}">
					<c:set var="aUrl"
						value="?boardId=${param.boardId}&page=${prevPage}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
					<a href="${aUrl}"><span>◀이전</span></a>
				</c:if>

				<c:forEach var="i" begin="${pageBoxStartPage}"
					end="${pageBoxEndPage}" step="1">
					<c:set var="aClass" value="${page == i ? 'red' : '' }" />
					<c:set var="aUrl"
						value="?boardId=${param.boardId}&page=${i}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />

					<a class="${aClass}" href="${aUrl}"><span>${i}</span></a>
				</c:forEach>

				<c:if test="${needToShowNextPageBox}">
					<c:set var="aUrl"
						value="?boardId=${param.boardId}&page=${nextPage}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}" />
					<a href="${aUrl}"><span>다음▶</span></a>
				</c:if>
			</div>
		</div>
	</div>
</section>
<!-- 섹션 끝-->

<%@ include file="../../part/foot.jspf"%>