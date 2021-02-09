<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="${article.title}" />

<%@ include file="../../part/head.jspf" %>

	<style>
		section{
  flex-grow : 1; 
}

.detail-box > .con{
  margin-top : 130px;
  width : 70%;
  color : whitesmoke;
}

.detail-box > .con > div{
  margin-bottom: 1rem; 
}

.detail-box > .con > .detail-title{
  align-items: center;
  justify-content:space-between;
}

.detail-box > .con > .detail-title > span{
  font-size : 4vh;
  white-space : nowrap; 
  font-weight : 700;
}

.detail-box > .con > .detail-title > .detail-edit{
  margin-left : 5rem;
}

.detail-box > .con > .detail-title > .detail-edit > a{
  margin : 0 0.5rem;
  padding : 3px 10px;
  color : #808080;
  white-space : nowrap; 
  border : 1px solid #808080;
  font-size : 0.9rem;
}

.detail-box > .con > .detail-title >.detail-edit > a:hover{
  background-color : #808080;
  color : white;
}

.detail-box > .con > .detail-title > .detail-edit > a:active{
  background-color : #808080;
  color : white;
}



.detail-box > .con > .detail-info > span{
  margin-right : 30px;
  color : #808080;
  white-space : nowrap; 
}

.detail-box > .con > .detail-like{
  margin-top : 3rem;
  justify-content : center;
}

.detail-box > .con > .detail-like > a{
  font-size : 1.3rem;
  margin : 0 0.3rem;
}

.detail-box > .con > .detail-like > a > i{
  margin : 0 0.3rem;
}

.detail-box > .con > .detail-nav{
  margin: 3rem 0;
  justify-content : center;
}

.detail-box > .con > .detail-nav > a{
  margin : 0 0.5rem;
  display : block;
  padding : 3px 10px;
  color : #808080;
  white-space : nowrap; 
  border : 1px solid #808080;
  font-size : 0.9rem;
}


.detail-box > .con > .detail-nav > a:hover{
  background-color : #808080;
  color : white;
}

.detail-box > .con > .detail-nav > a:active{
  background-color : #808080;
  color : white;
}

.detail-box > .con > .detail-reply > .reply-box{
  margin-top : 1rem;
  flex-direction : column;
}

.detail-box > .con > .detail-reply >.reply-box> textarea{
  margin-top : 0.3rem;
  background-color:#282828;
  border:#282828;
  border-bottom : 1px solid #ceccc0;
  resize : none; 
  width : 100%;
  color : #ceccc0;
  font-size : 0.9rem;
}

.detail-box > .con > .detail-reply > .reply-box > input{
  margin-top : 0.5rem;
  width : 50px;
  height : 3vh;
  align-self : flex-end;
  text-align :center;
  white-space:nowrap
  border-color : #282828;
  border-radius : 3px;
  background-color : white;
  opacity : 0.5;
}

.detail-box > .con > .detail-reply > .reply-list{
  margin-top : 1.2rem;
  font-size : 0.8rem;
  flex-direction : column;
  width: 100%;
}

.detail-box > .con > .detail-reply > .reply-list > #reply-nickName{
  font-weight : 700;
}
.detail-box > .con > .detail-reply > .reply-list > #reply-body{
  white-space:pre-line;
}

.detail-box > .con > .detail-reply > .reply-list > .reply-edit{
  margin-top : 1rem;
}

.detail-box > .con > .detail-reply > .reply-list > .reply-edit > a{
  margin-right : 0.5rem;
  padding : 3px 10px;
  color : #808080;
  white-space : nowrap; 
  border : 1px solid #808080;
}

.detail-box > .con > .detail-reply > .reply-list > .reply-edit > a:hover{
  background-color : #808080;
  color : white;
}

.detail-box > .con > .detail-reply > .reply-list > .reply-edit > a:active{
  background-color : #808080;
  color : white;
}

.detail-box > .con > .detail-reply > .reply-list > .reply-like{
  margin-top : 1rem;
}

.detail-box > .con > .detail-reply > .reply-list > .reply-like > a{
  font-size : 0.9rem;
  margin-right : 0.5rem;
}

.detail-box > .con > .detail-reply > .reply-list > .reply-like a > i {
  margin-right : 0.5rem;
}

/*디테일 모바일 버전*/ 
@media(max-width:800px){
  .detail-info{
    flex-direction:column;
  }
  .detail-nav_mobile{
    flex-direction:column;
    margin : 2rem 0;
  }

  .detail-nav_mobile > a{
    margin-bottom: 0.5rem;
    text-align : center;
    color : #808080;
    white-space : nowrap; 
    border : 1px solid #808080;
    font-size : 0.9rem;
  }
  
  .detail-nav_mobile > a:hover{
     background-color : #808080;
  color : white;
  }
  
  .detail-nav_mobile > a:active{
     background-color : #808080;
  color : white;
  }
}
		
	</style>
 <section>
      <div class="detail-box con-min-width">
        <div class="con">
          <div class="detail-title flex">
            <span>${article.title}</span>
            
            <c:if test="${sessionScope.loginedMemberId == article.memberId }">
            <div class="detail-edit visible-md-up">
              <a href="modify?boardId=${param.boardId}&id=${param.id}&memberId=${article.memberId}">글 수정</a>
              <a href="/jspCommunity/usr/article/doDelete?boardId=${param.boardId}&id=${param.id}&memberId=${article.memberId}">글 삭제</a>
            </div>
            </c:if>
            
          </div>
          <div class="detail-info flex">
            <span>작성자 : ${article.extra__writer}</span>
            <span>조회수 ${article.count}</span>
            <span>작성일 :${article.regDate}</span>
          </div>
          <div class="detail-body">
            ${article.body}
          </div>
          
          <!-- 좋아요 싫어요 -->
          <div class="detail-like flex">
           <a href="../like/doLike?boardId=${board.id}&memberId=${loginedMemberId}&id=${article.id}">
		
				<c:if test="${liked == false}">
					<i class="far fa-heart"></i>${like}
				</c:if>
				
				<c:if test="${liked}">
					<i class="fas fa-heart"></i>${like}
				</c:if>
				
				</a>
				
			
				<a href="../like/doDislike?boardId=${board.id}&memberId=${loginedMemberId}&id=${article.id}">
				
				<c:if test="${disliked == false}">
					<i class="far fa-thumbs-down"></i>${dislike}
				</c:if>
				
				<c:if test="${disliked}">
					<i class="fas fa-thumbs-down"></i>${dislike}
				</c:if>
		</a>
          </div>
          
          <!-- 게시글 nav -->
          <nav class="detail-nav flex visible-md-up">
            <a href="">이전글</a> 
            <a href="list?boardId=${article.boardId}">리스트로 돌아가기</a>
            <a href="">다음글</a> 
          </nav>
          
          <nav class="detail-nav_mobile flex visible-sm-down">
            <a href="">글 수정</a>
            <a href="">글 삭제</a>
            <a href="">이전글</a> 
            <a href="">리스트로 돌아가기</a>
            <a href="">다음글</a> 
          </nav>

          
          <!-- 댓글 -->
          <span>댓글 ${replyCount}개</span>

          <div class="detail-reply">
          
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

            <form class="reply-box flex" action="../reply/doReply" methods="POST" onsubmit="DoReplyForm__submit(this); return false;">
            <input type="hidden" name="id" value="${param.id}"/>
			<input type="hidden" name="boardId" value="${param.boardId}"/>
		
              <span>${loginedMember.nickName}</span>
              <textarea name="body"></textarea>
              <input type="submit" value="댓글">
            </form>

		<c:forEach items="${replies}" var="reply" >
            <div class="reply-list flex">
              <span id="nickName">${reply.extra__nickName}</span>
              <div id="reply-body">${reply.body}</div>
              <c:if test="${sessionScope.loginedMemberId == reply.memberId }">
              
              <div class="reply-edit">
				<a href="../reply/modify?id=${param.id}&boardId=${param.boardId}&replyId=${reply.id}&&memberId=${reply.memberId}">수정하기</a>
				<a href="../reply/doDelete?id=${param.id}&boardId=${param.boardId}&replyId=${reply.id}&&memberId=${reply.memberId}">삭제하기</a>
              </div>
              </c:if>
              
              <!-- 댓글 좋아요 싫어요 -->
              <div class="reply-like">
               <a href="../reply/doLike?id=${article.id}&boardId=${board.id}&memberId=${loginedMemberId}&replyId=${reply.id}">
		
				<c:if test="${likedReply == false}">
					<i class="far fa-heart"></i>${likeReplyCount}
				</c:if>
				
				<c:if test="${likedReply}">
					<i class="fas fa-heart"></i>${likeReplyCount}
				</c:if>
				
				</a>
				
			
				<a href="../reply/doDislike?id=${article.id}&boardId=${board.id}&memberId=${loginedMemberId}&replyId=${reply.id}">
				
				<c:if test="${dislikedReply == false}">
					<i class="far fa-thumbs-down"></i>${dislikeReplyCount}
				</c:if>
				
				<c:if test="${dislikedReply}">
					<i class="fas fa-thumbs-down"></i>${dislikeReplyCount}
				</c:if>
		</a>
              </div>
            </div> 
		</c:forEach>
		
          </div>

          </section>
        <!-- 섹션 끝-->
	
<%@ include file="../../part/foot.jspf" %>