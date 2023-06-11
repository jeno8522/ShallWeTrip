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
            <div class="text">비밀번호 찾기 결과</div>
            <div class="small-text">ENJOYTRIP</div>
            <div class="form-container">
            	<c:if test = "${empty passwordResult}">
            		<div class="mt-5 mb-5 text-center">
            			일치하는 회원 정보가 존재하지 않습니다.
            		</div>
            	</c:if>
            	<c:if test = "${!empty passwordResult}">
            		<form method="post" action="${root}/user" id="update">
			       	<input type="hidden" name="action" value="update">           
		            <div class="form-container mt-5">
		                <input type="hidden" id="user-id" name="id" value="${passwordResult.id}">
		                <input type="hidden" id="user-name" name="name" value="${passwordResult.name}">
		                <input type="hidden" id="user-nickname" name="nickname" value="${passwordResult.nickname}">
		                <input type="hidden" id="user-email" name="email" value="${passwordResult.email}" readonly>
		                <div class="data">
		                    <label>새 비밀번호</label>
		                    <input type="password" id="user-new-password" name="pw" placeholder="(문자, 숫자 포함 8~20자)">
		                </div>
		                <div class="data">
		                    <label>새 비밀번호 확인</label>
		                    <input type="password" id="user-new-password-check" name ="pwCheck">
		                </div>
		                <div class="text-danger" id="match-new-password"></div>
		                <div class="text-center mt-3 row">
		                    <div class="col-12">
		                        <a class="btn btn-primary btn-cta register-btn" onclick="submitChange()" target="">저장</a>
		                    </div>
		                </div>
		            </div>
		           	</form>
           		</c:if>
            </div>
            
        
        </div>
    </div>

    <%@ include file="/common/footer.jsp" %>
</div>
</body>
<script>
	var newMatch = false;
    var pw = document.getElementById("user-new-password");
	var checkPw = document.getElementById("user-new-password-check");
    var matchNewPw = document.getElementById("match-new-password");
    var matchCurrentPw = document.getElementById("match-current-password");
	checkPw.addEventListener("change", function() {
        if (pw.value != checkPw.value) {
            matchNewPw.innerHTML = "비밀번호가 일치하지 않습니다."
            newMatch = false;
            return;
        }
        matchNewPw.innerHTML = "";
        newMatch = true;
    })
    function submitChange() {
        if (newMatch) { //비밀번호를 변경하는 경우
            document.getElementById('update').submit();
        }
    }
</script>
</html>
