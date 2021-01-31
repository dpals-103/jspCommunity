<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Main" />

<%@ include file="../../part/head.jspf"%>

<style> 
section{
  flex-grow : 1; 
}

.main-box > .con{
  flex-direction: column;
  align-items : center;
}

.main-box > .con > .main-box__title{
  flex-direction :column;
}

.main-box > .con > .main-box__title{
  flex-direction :column;
  align-items: center;
  margin-top : 130px;
}

.main-box > .con > .main-box__title > #title{
  color : #9f898c;
  font-family: 'Lora', serif;
  font-weight : 700;
  letter-spacing : -0.5px;
  font-size : 8vh;
  white-space : nowrap;
}

.main-box > .con > .main-box__title > #sub-title{
  color : #9f898c;
  font-family: 'Noto Sans KR', sans-serif
  font-weight : 400;
  letter-spacing : 6px;
  margin-left : 1rem;
  font-size : 2.5vh;
  white-space : nowrap;
}

.main-box > .con > .main-box__icon{
  color : #9f898c;
  font-size : 2rem;
  margin-top : 1.5rem;
  margin-left : 1rem;
}

.main-box > .con > .main-box__icon > span:nth-child(2){
  margin : 0 5rem;
}

.main-box > .con > .main-box__menu-btn{
  margin-top : 5rem;
}

.main-box > .con > .main-box__menu-btn > .menu-btn{
  margin-left : 1rem;
  width : 15rem;
  text-align : center;
  border : solid 0.8px #ceccc0;

}

.main-box > .con > .main-box__menu-btn > .menu-btn > a{
  color : #ceccc0;
  display : block; 
  padding : 0.3rem 0;
}

.main-box > .con > .main-box__menu-btn > .menu-btn > a:hover{
  color : #282828;
  background-color: #ceccc0;
}

@media(max-width:800px){
  .main-box__menu-btn > .menu-btn{
    margin-bottom : 1rem;
  }
}

@media(min-width:801px){
  .main-box__menu-btn {
   display : flex;
  }
}
</style>
 <section>
      <div class="main-box con-min-width">
        <div class="con flex">
          <div class="main-box__title con-min-width flex">
            <span id="title">our record</span>
            <span id="sub-title">우리가 만드는 플레이리스트</span>
          </div>
          <div class="main-box__icon">
            <span><i class="fas fa-step-backward"></i></span>
            <span><i class="fas fa-play"></i></span>
            <span><i class="fas fa-step-forward"></i></span>
          </div>
          <div class="main-box__menu-btn">
            <div class="menu-btn visible-md-up">
              <a href=""><span>이번주 주제</span></a>
            </div>
            
            <div class="menu-btn visible-sm-down">
              <a href=""><span>이번주 주제</span></a>
            </div>
            
            
            <div class="menu-btn visible-md-up">
              <a href=""><span>our record_Playlist</span></a>
            </div>
            
            <div class="menu-btn visible-sm-down">
              <a href=""><span>our record_Playlist</span></a>
            </div>
          </div>
        </div>
      </div>
    </section>




<%@ include file="../../part/foot.jspf"%>