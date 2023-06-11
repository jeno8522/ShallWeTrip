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
            글 수정
          </h2>
        </div>
        <div class="article-section col-lg-8 col-md-10 col-sm-12">
          <form id="form-modify" method="post" action="${root}/info/modify">
<%--           	<input type='hidden'	name='pageNo'	value='${bean.pageNo}'/>
			<input type='hidden'	name='key'		value='${bean.key}'/>
			<input type='hidden'	name='word'		value='${bean.word}'/> --%>
          	<input type="hidden" name="action" value="modify">
          	<input type="hidden" name="infoId" value="${infoBoard.infoId}">
            <div class="mb-3">
              <label for="title" class="form-label">제목 : </label>
              <input type="text" class="form-control" id="title" name="title" value="${infoBoard.title}" />
            </div>
            <div class="mb-3">
              <label for="content" class="form-label">내용 : </label>
              <textarea class="form-control" id="content" name="content" rows="7">${infoBoard.content}</textarea>
            </div>
            <div class="col-auto text-center">
              <a id="btn-modify" class="btn btn-primary " type="button" 
	            	onclick="">
	            	 글수정
	            </a>
              <a id="btn-list" class="btn btn-primary " type="button" 
	            	onclick="">
	            	 목록
	            </a>
            </div>
          </form>
        </div>
      </div>
    </div>
    <script>
      document.querySelector("#btn-modify").addEventListener("click", function () {
        if (!document.querySelector("#title").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        } else {
	         let form = document.querySelector("#form-modify");
          form.setAttribute("action", `${root}/info/modify`);
          form.submit();

        }
      });
      document.querySelector("#btn-list").addEventListener("click", function () {
       
        location.href = "${root}/info/infoList";
  
    	  
      });
    </script>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
  </div>
</body>
</html>