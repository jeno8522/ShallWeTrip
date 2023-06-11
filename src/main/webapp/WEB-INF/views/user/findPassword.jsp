<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="center" id="body-content">
        <div class="container"  id="log-in">
        
            <div class = "spacing"></div>
            <div class="text">비밀번호 찾기</div>
            <div class="small-text">ENJOYTRIP</div>
            <div class="form-container">
            	<form id = "find-password-form" action="${root}/user" method="post">
            		<input type="hidden" name="action" value="findPassword">
	                <div class="data">
	                    <label>아이디</label>
	                    <input type="text" id="loginId" name="id" required="required" >
	                </div>
	                <div class="data">
	                    <label>이메일</label>
	                    <input type="email" id="email" name="email">
	                </div>
	                <div class="data">
	                    <label>이름</label>
	                    <input type="text" id="name" name="name">
	                </div>
	                <div class="text-center mt-3">
	                    <a class="btn btn-primary btn-cta register-btn" onclick="document.getElementById('find-password-form').submit()">비밀번호 찾기</a>
	                </div>
	                <div class="text-center mt-3">
	                    <a class="btn btn-primary btn-cta register-btn" href="${root}/user?action=loginForm" target="">뒤로가기</a>
	                </div>
            	</form>
            </div>
        </div>
    </div>

    <%@ include file="/common/footer.jsp" %>
</div>
</body>
</html>