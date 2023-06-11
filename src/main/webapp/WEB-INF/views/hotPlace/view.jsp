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
			<c:if test="${hotPlace eq null}">
				<script>
				alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
				location.href = "${root}/hotPlace/hotList";
				</script>
			</c:if>
      <div class="row justify-content-center">
        <div class="hotPlace-section col-lg-8 col-md-10 col-sm-12">
          <div class="row my-2">
            <h2 class="text-secondary">${hotPlace.hotId}. ${hotPlace.title}</h2>
          </div>
          <div class="row">
            <div class="col-md-8">
              <div class="clearfix align-content-center">
                <img
                  class="avatar me-2 float-md-start bg-light p-2"
                  src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                />
                <p>
                  <span class="fw-bold">${hotPlace.userId}</span> <br />
                  <span class="text-secondary fw-light"> ${hotPlace.createDate} 조회 : ${hotPlace.hit} </span>
                </p>
              </div>
            </div>
            <div class="col-md-4 align-self-center text-end">댓글 : 17</div>
            <div class="divider mb-3"></div>
            <div class="text-secondary">
              ${hotPlace.content}
            </div>
<%--             <div>${hotPlace.fileInfos[0].saveFolder }</div> --%>
            <%-- <div>${article }</div> --%>
            <c:if test="${!empty hotPlace.fileInfos }">
            	<div class="mt-3">
            		<ul>
            		
            			<c:forEach var="file" items="${hotPlace.fileInfos }">
             				<img src="${root }/upload/image/${file.saveFile }">
            			
            			</c:forEach>
            		
            		</ul>
            	
            	
            	</div>
            
            
            </c:if>
            <div class="divider mt-3 mb-3"></div>
            <div class="d-flex justify-content-end">
              <a id="btn-list" class="btn btn-primary " type="button" 
	            	onclick="">
	            	목록
	            </a>
              <c:if test="${(userInfo.id eq hotPlace.userId) or (userInfo.id eq 'admin')}">
              <a id="btn-mv-modify" class="btn btn-primary ms-1" type="button" 
	            	onclick="">
	            	수정
	            </a>
              <button type="button" id="btn-delete" class="btn btn-outline-danger ms-1">
                삭제
              </button>
              </c:if>
            </div>
          </div>
        </div>
      </div>
    </div>
      <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </div>
    <script>
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "${root}/hotPlace/hotList";
      });
      document.querySelector("#btn-mv-modify").addEventListener("click", function () {
        alert("글수정하자!!!");
        location.href = "${root}/hotPlace/modify?hotId=${hotPlace.hotId}";
      });
      document.querySelector("#btn-delete").addEventListener("click", function () {
        alert("글삭제하자!!!");
        location.href = "${root}/hotPlace/delete?hotId=${hotPlace.hotId}";
      });
    </script>
</body>
</html>