<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:set var="pageTitle" value="게시물작성하기" />

<style>
section{
  flex-grow : 1; 
}

.write-box{
  margin-top : 130px;
  flex-direction:column;
}

.write-box > span{
  font-size : 4vh;
  font-weight : 700;
  color : white;
}

.write-box > form{
  margin-top : 2rem;
}

.write-box > form > input[name="title"]{
  width : 100%;
  height : 3vh;
}

.write-box > form > .toast-ui-editor{
  margin : 1rem 0;
}


.write-box > form > .write-btn{
  justify-content : center;
}
.write-box > form > .write-btn > input[value="작성완료"],
.write-box > form > .write-btn > button{
  color : #808080;
  background-color : #282828;
  border : solid 1px #808080;
  padding : 0.3rem;
  margin-right : 0.5rem;
  width: 30vw;
}

</style>
<%@ include file="../../part/head.jspf" %>

	 <section>
      <div class="con-min-width">
        <div class="con write-box flex">
          <span>Write _ 자유게시판</span>
          
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

		const editor = $(form).find


		('.toast-ui-editor').data('data-toast-editor');
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
          
          
          <form action="/jspCommunity/usr/article/doWrite" methods="POST" onsubmit="DoWriteForm__submit(this); return false;" >
            <input type="hidden" name="boardId" value="${param.boardId}" />
            <input type="hidden" name="body"/>
            <input type="text" name="title" placeholder="제목을 입력하세요" maxlength="30"/>
            <script type="text/x-template"></script>
            <div class="toast-ui-editor" id="editor-1"></div>
            <div class="write-btn flex">
              <input type="submit" value="작성완료"/>
              <button type="button" onclick="history.back();"/>뒤로가기</button>	
            </div>
          </form>
      </div>


    </section>
	
<%@ include file="../../part/foot.jspf" %>