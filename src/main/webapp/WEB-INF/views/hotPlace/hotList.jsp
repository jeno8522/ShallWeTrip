<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="/WEB-INF/views/common/head.jsp" %>
    <!-- Page CSS -->  
    <link id="page-style" rel="stylesheet" href="${root}/assets/css/main2.css">	
  </head>
  <body>
    <!-- ******HEADER****** --> 
    <header id="header" class="header">  
          <%@ include file="/WEB-INF/views/common/nav.jsp" %>         
    </header><!--//header-->
    <div id="body-wrapper">
        <div class="plan-section" id="body-content">
          <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10 col-sm-12">
              <h2 class="my-3 py-3 text-center">
               	 Hot Place~~!!
              </h2>
            </div>
            <div class="col-lg-8 col-md-10 col-sm-12">
              <div class="row align-self-center mb-2">
                <div class="col-md-3 text-start">
                  <a id="btn-mv-register" class="btn btn-primary" type="button" 
	            	    onclick="">
	            	    새로운 글 작성
	                </a>
                </div>
                <div class="col-md-7 offset-2">
                  <form class="d-flex" id="form-search" action="">
                    <input type="hidden" name="action" value="list"/>
                    <input type="hidden" name="pgno" value="1"/>
                    
                    <select
                      name="order"
                      id="order"
                      class="form-select form-select-sm ms-5 me-1 w-50"
                      aria-label="정렬조건"
                    >
                      <option selected value="">정렬조건</option>
                      <option value="newest">최신등록순</option>
                      <option value="view">조회수</option>
                      <option value="recommended">추천순</option>
                    </select>
                    
                    <select
                      name="key"
                      id="key"
                      class="form-select form-select-sm me-1 w-50"
                      aria-label="검색조건"
                    >
                      <option selected value="">검색조건</option>
                      <option value="hotId">글번호</option>
                      <option value="title">제목</option>
                      <option value="userId">작성자</option>
                    </select>
                    <div class="input-group input-group-sm">
                      <input type="text" name="word" id="word" class="form-control" placeholder="검색어..." />
                      <button id="btn-search" class="btn btn-dark" type="button">검색</button>
                    </div>
                  </form>
                </div>
              </div>
              <table class="table table-hover">
                <thead>
                  <tr class="text-center">
                    <th scope="col">글번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">조회수</th>
                    <th scope="col">작성일</th>
                  </tr>
                </thead>
                <tbody>    
                <c:forEach var="hotPlace" items="${hotPlaces}"> 
                  <tr class="text-center article-row" data-no="${hotPlace.hotId}">
                    <th scope="row">${hotPlace.hotId}</th>
                    <td>${hotPlace.title}</td>
                    <td>${hotPlace.userId}</td>
                    <td>${hotPlace.hit}</td>
                    <td>${hotPlace.createDate}</td>
                  </tr>  
                </c:forEach>   
                </tbody>
              </table>
              <div class="row navigation">
              	${hotPlacePageBean.pageLink }
            	</div>
            </div>
            
          </div>
      </div>
    <form id="form-param" method="get" action="">
      <input type="hidden" id="p-order" name="order" value="">
      <input type="hidden" id="p-pgno" name="pageNo" value="">
      <input type="hidden" id="p-key" name="key" value="">
      <input type="hidden" id="p-word" name="word" value="">
    </form>
    
    <script>
      let titles = document.querySelectorAll(".article-row");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
          console.log(this.getAttribute("data-no"));
          location.href = "${root}/hotPlace/view?hotId=" + this.getAttribute("data-no");
        });
      });

      document.querySelector("#btn-mv-register").addEventListener("click", function () {
		<c:if test='${!empty userInfo}'>
			location.href = "${root}/hotPlace/write";
		</c:if>
		<c:if test='${empty userInfo}'>
			location.href = "${root}/user/login";
		</c:if>
      });
      
      document.querySelector("#btn-search").addEventListener("click", function () {
    	  let form = document.querySelector("#form-search");
          form.setAttribute("action", "${root}/hotPlace/hotList");
          form.submit();
      });
      
      let pages = document.querySelectorAll(".page-link");
      pages.forEach(function (page) {
        page.addEventListener("click", function () {
          console.log(this.parentNode.getAttribute("data-pg"));
       	  document.querySelector("#p-pgno").value = this.getAttribute('data-pg');
       	  document.querySelector("#p-order").value = "${param.order}";
       	  document.querySelector("#p-key").value = "${param.key}";
       	  document.querySelector("#p-word").value = "${param.word}";
          document.querySelector("#form-param").submit();
        });
      });
    </script>
  <%@ include file="/WEB-INF/views/common/footer.jsp" %>
  </div>
  </body>
</html>