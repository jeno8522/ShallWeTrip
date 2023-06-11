<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
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
<!-- https://www.myro.co.kr/login -->
<div id="body-wrapper">
    <div class="center" id="body-content">
        <div class="container"  id="log-in">
        
            <div class = "spacing"></div>
            <div class="text">LOG IN</div>
            <div class="small-text">ENJOYTRIP</div>
            <div class="form-container">
            	<form id = "login-form" action="${root}/user/login" method="post">
	                <div class="data">
	                    <label>아이디</label>
	                    <input type="text" id="loginId" name="id" required="required" >
	                </div>
	                <div class="data">
	                    <label>비밀번호</label>
	                    <input type="password" id="loginPw" name="pass">
	                </div>
	                <c:if test = "${!empty failMsg}">
	                	<div class = "text-danger mb-3">
	                		${failMsg}
	                	</div>
	                </c:if>
	                <div class="forgot-pass text-center">
	                    <a href="${root}/user?action=findPasswordForm">비밀번호를 잊으셨나요?</a>
	                </div>
	                <div class="text-center mt-3">
	                    <a class="btn btn-primary btn-cta register-btn" onclick="document.getElementById('login-form').submit()">로그인</a>
	                </div>
	                <div class="signup-link text-center mt-2">${param.failMsg}<a href="${root}/user/regist">회원가입</a></div>
            	</form>
            </div>
        
        </div>
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
</body>
</html>

<script>
    <%-- request 객체에 msg가 들어있을 때 해당 내용 알림창 띄우기 --%>
    <c:if test="${!empty param.failMsg}">
	alert("${param.failMsg}");
	</c:if>
</script>