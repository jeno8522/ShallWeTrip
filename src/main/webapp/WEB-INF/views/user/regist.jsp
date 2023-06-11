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
        <div class="container"  id="sign-up">
            <div class = "spacing"></div>
            <div class="text">SIGN UP</div>
            <form method="post" action="${root}/user/regist" id="registForm">
	            <div class="small-text">ENJOYTRIP</div>
	            <div class="form-container">
	                <div class="data">
	                    <label>아이디</label>
	                    <input type="text" id="id" name="id" required="">
	                    <!-- <div id="idcheck-result"></div>-->
	                </div>
					
	                <div class="data">
	                    <label>이름</label>
	                    <input type="text" id="name" name="name" placeholder="">
	                </div>
	                <div class="data">
	                    <label>닉네임</label>
	                    <input type="text" id="nick" name="nickname" onkeypress="javascript:if(event.keyCode==13) {myroLogin()}" required="">
	                </div>
	                <div class="data">
	                    <label>비밀번호</label>
	                    <input type="password" id="pass" name="pass" placeholder="비밀번호(문자, 숫자 포함 8~20자)">
	                </div>
					<div class="data">
	                    <label>비밀번호 확인</label>
	                    <input type="password" id="pw-check" name="pw-check">
	                    <div class="text-danger" id="match-password"></div>
	                </div>
	               	<div class="data">
	                    <label>이메일</label>
	                    <input type="email" id="email" name="email" placeholder="">
	                </div>
	                <!-- 
	                <div class="data">
	                    <label>비밀번호 확인</label>
	                    <input type="password" id="myroLoginPwd" onkeypress="javascript:if(event.keyCode==13) {myroLogin()}" required="">
	                </div>
	                -->
	                <div class="text-center mt-3">
	                    <a class="btn btn-primary btn-cta register-btn" onclick="submitForm()">회원가입</a>
	                </div>
	                <div class="text-center mt-3">
	                    <a class="btn btn-primary btn-cta register-btn" href="${root}/user/login" target="">뒤로가기</a>
	                </div>
	            </div>
	        </form>
        </div>
    </div>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
</body>
</html>
<script>
	var isPossibleId = true;
	var doesPwMatch = false;
	var idInput = document.getElementById("id");
	
    var pwInput = document.getElementById("pass");
	var checkPwInput = document.getElementById("pw-check");
	var matchPwResult = document.getElementById("match-password");

	/*
	idInput.addEventListener("change", function() {
		let resultDiv = document.querySelector("#idcheck-result");
		fetch("${root}/user?action=idcheck&userid=" + idInput.value)
	   		.then(response => response.text())
	   		.then(data => {
		 		if(data == 0) {
		   			resultDiv.setAttribute("class", "text-primary");
	       			resultDiv.textContent = "사용 가능한 아이디입니다.";
	       			isPossibleId = true;
		 		} else {
		   			resultDiv.setAttribute("class", "text-danger");
 		       		resultDiv.textContent = "이미 사용 중인 아이디입니다.";
 		     		isPossibleId = false;
		 		}
  		   });
    })
    */
	checkPwInput.addEventListener("change", function() {
        if (pwInput.value != checkPwInput.value) {
            matchPwResult.innerHTML = "비밀번호가 일치하지 않습니다."
            doesPwMatch = false;
            return;
        }
        matchPwResult.innerHTML = "";
        doesPwMatch = true;
    })
    function submitForm() {
        if (isPossibleId && doesPwMatch) { 
            matchPwResult.innerHTML = "";
            document.getElementById('registForm').submit();
        }
    }
</script>