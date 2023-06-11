<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
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
      <div class="row justify-content-center" >
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="text-center">
           	 여행 정보 글 작성
          </h2>
        </div>
        <div class="article-section col-lg-8 col-md-10 col-sm-12">
          <form id="form-register" method="POST" action="${root}/info/infoList">
            <div class="mb-3">
              <label for="title" class="form-label">제목 : </label>
              <input
                type="text"
                class="form-control"
                id="title"
                name="title"
                placeholder="제목..."
              />
            </div>
            <div class="mb-3">
              <label for="content" class="form-label">내용 : </label>
              <textarea class="form-control" id="content" name="content" rows="7"></textarea>
            </div>
            <div class="col-auto text-center">
				<a id="btn-register" class="btn btn-primary" type="button" 
					onclick="">
					글 작성
				</a>
              <button type="reset" class="btn btn-outline-danger">초기화</button>
            </div>
          </form>
        </div>
      </div>
      </div>
      <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </div>
    <script>
      document.querySelector("#btn-register").addEventListener("click", function () {
        if (!document.querySelector("#title").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        } else {
          let form = document.querySelector("#form-register");
          form.setAttribute("action", "${root}/info/write");
          form.submit();
        }
      });
    </script>
</body>
</html>
