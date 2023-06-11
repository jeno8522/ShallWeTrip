<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/head.jsp" %>
	<!-- Page CSS -->  
	<link id="page-style" rel="stylesheet" href="${root}/assets/css/main2.css">	
</head>
<body>
	<!-- ******HEADER****** --> 
    <header id="header" class="header">  
        <%@ include file="/common/nav.jsp" %>         
    </header><!--//header-->
    <div id="body-wrapper">
    	<div class="plan-section" id="body-content">
        <div class="plan-body container text-center">
            <h2 class="fs-2 search-title">나만의 여행 계획</h2>
            <div class = "row mt-5 mb-2 justify-content-between">
                <div class = "mr-auto ml-2 col-auto mb-2">
                    <a class="btn btn-primary btn-cta" id="btn-mv-register">
                        	여행 계획 만들기
                    </a>
                </div>
                <div class = "mr-auto col-auto">
                <div class="col-md-7 offset-3">
	              <form class="d-flex" id="form-search" action="">
	                <input type="hidden" name="action" value="list"/>
	                <input type="hidden" name="pgno" value="1"/>
	                <select
	                  name="key"
	                  id="key"
	                  class="form-select form-select-sm ms-5 me-1 w-50"
	                  aria-label="검색조건"
	                >
	                  <option selected>검색조건</option>
	                  <option value="plan_no">글번호</option>
	                  <option value="subject">제목</option>
	                  <option value="user_id">작성자</option>
	                </select>
	                <div class="input-group input-group-sm">
	                  <input type="text" name="word" id="word" class="form-control" placeholder="검색어..." />
	                  <button id="btn-search" class="btn btn-dark" type="button">검색</button>
	                </div>
	              </form>
	            </div>
                    <form class="d-flex">
                        <select class="form-select me-sm-2">
                            <option value="">검색조건</option>
                            <option value="title">제목</option>
                            <option value="user_id">작성자</option>
                        </select>
                        <input class="form-control me-sm-2" type="search" placeholder="Search">
                        <a class="btn btn-secondary my-2 my-sm-0" type="submit">Search</a>
                    </form>
                </div>
                <div></div>
            </div>
            <div class="plans-wrapper row">
            	<c:forEach var="plan" items="${plans}">    
            	<div class="item col-md-6 col-12 mb-3">
                    <a href="${root}/plan?action=view&planNo=${planNo}"> <!-- 클릭 시 링크 설정 -->
                        <div class="card">
                            <!-- 카드 헤더 -->
                            <div class="card-header" style="background-image: url('../assets/images/main2.png');">
                    
                            </div>
                            <!--  카드 바디 -->
                            <div class="card-body">
                                <!--  카드 바디 헤더 -->
                                <div class="card-body-header">
                                    <h1>${plan.subject}</h1>
                                    <p class="card-body-hashtag">#제주 #유채꽃</p>
                                    <p class = "card-body-nickname">작성자: ${plan.userId}</p>
                                </div>
                                <pre class="card-body-description">${plan.content}</pre>
                                <!--  카드 바디 본문 -->
                                <!--  카드 바디 푸터 -->
                                <div class="card-body-footer row text-center">
                                    <hr style="margin-left: 10px; margin-bottom: 8px; opacity: 0.5; border-color: #4d4c4b">
                                    <div class="col-4">${plan.hit}</div>
                                    <div class="col-4">댓글 4개</div>
                                    <div class="col-4">${plan.registerTime}</div>
                                </div>
                            </div>
                        </div>
                     </a>
                </div>
	          
				</c:forEach>   
            </div>
            <div class="row">
          		${navigation.navigator}
        	</div>
        </div>
    </div>
    <form id="form-param" method="get" action="">
      <input type="hidden" id="p-action" name="action" value="">
      <input type="hidden" id="p-pgno" name="pgno" value="">
      <input type="hidden" id="p-key" name="key" value="">
      <input type="hidden" id="p-word" name="word" value="">
    </form>
    <script>
      document.querySelector("#btn-mv-register").addEventListener("click", function () {
    	<c:if test='${!empty userInfo}'>
    	 	location.href = "${root}/plan?action=mvwrite";
    	</c:if>
    	<c:if test='${empty userInfo}'>
	 		location.href = "${root}/user?action=loginForm";
		</c:if>
      });
      
      document.querySelector("#btn-search").addEventListener("click", function () {
    	  let form = document.querySelector("#form-search");
          form.setAttribute("action", "${root}/plan");
          form.submit();
      });
      
      let pages = document.querySelectorAll(".page-link");
      pages.forEach(function (page) {
        page.addEventListener("click", function () {
          console.log(this.parentNode.getAttribute("data-pg"));
          document.querySelector("#p-action").value = "list";
       	  document.querySelector("#p-pgno").value = this.parentNode.getAttribute("data-pg");
       	  document.querySelector("#p-key").value = "${param.key}";
       	  document.querySelector("#p-word").value = "${param.word}";
          document.querySelector("#form-param").submit();
        });
      });
    </script>
    <%@ include file="/common/footer.jsp" %> 
</div>
</body>
</html>