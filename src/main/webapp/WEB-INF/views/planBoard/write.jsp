<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <div class="row justify-content-center" >
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="text-center">
           	 여행 계획 만들기
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <form id="form-register" method="POST" action="">
          	<input type="hidden" name="action" value="write">
            <div class="mb-3">
              <label for="subject" class="form-label">제목 : </label>
              <input
                type="text"
                class="form-control"
                id="subject"
                name="subject"
                placeholder="제목..."
              />
            </div>
            <div class="mb-3">
              <label for="content" class="form-label">내용 : </label>
              <textarea class="form-control" id="content" name="content" rows="7"></textarea>
            </div>
            <div class="col-auto text-center">
              <button type="button" id="btn-register" class="btn btn-outline-primary mb-3">
               	 글작성
              </button>
              <button type="reset" class="btn btn-outline-danger mb-3">초기화</button>
            </div>
          </form>
        </div>
      </div>
      </div>
      <%@ include file="/common/footer.jsp" %>
    </div>
    <script>
      document.querySelector("#btn-register").addEventListener("click", function () {
        if (!document.querySelector("#subject").value) {
          alert("제목을 입력해주세요.");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력을 입력해주세요.");
          return;
        } else {
          let form = document.querySelector("#form-register");
          form.setAttribute("action", "${root}/plan");
          form.submit();
        }
      });
    </script>
</body>
</html>
