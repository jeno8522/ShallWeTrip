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
            <div class="text">회원정보</div>
            <div class="small-text">ENJOYTRIP</div>
            <form method="post" action="${root}/user/update" id="update">
            <div class="form-container mt-5">
                <label class="label-title">기본정보</label>
                    <input type="hidden" id="user-id" name="id" value="${userInfo.id}">
                <div class="data">
                    <label>이름</label>
                    <input type="text" id="user-name" name="name" value="${userInfo.name}">
                </div>
                <div class="data">
                    <label>닉네임</label>
                    <input type="text" id="user-nickname" name="nickname" value="${userInfo.nickname}">
                </div>
                <div class="data">
                    <label>이메일</label>
                    <input type="text" id="user-email" name="email" value="${userInfo.email}" readonly>
                </div>
                <label class="label-title mt-5">비밀번호</label>
                <div class="data" style="display:none;">
                    <label>현재 비밀번호</label>
                    <input type="password" id="user-password">
                </div>
                <div class="text-danger" id="match-current-password"></div>
                <div class="data">
                    <label>새 비밀번호</label>
                    <input type="password" id="user-new-password" name="pass" placeholder="(문자, 숫자 포함 8~20자)">
                </div>
                <div class="data">
                    <label>새 비밀번호 확인</label>
                    <input type="password" id="user-new-password-check" name ="pwCheck">
                </div>
                <div class="text-danger" id="match-new-password"></div>
                <div class="text-center mt-3">
                    <a class="btn btn-danger btn-sm register-btn" href="${root}/user/remove?id=${userInfo.id}" target="">회원 탈퇴</a>
                </div>
                <div class="text-center mt-3 row">
                    <div class="col-6">
                        <a class="btn btn-primary btn-cta register-btn" href="${root}/index.jsp" target="">취소</a>
                    </div>
                    <div class="col-6">
                        <a class="btn btn-primary btn-cta register-btn" onclick="submitChange()" target="">저장</a>
                    </div>
                </div>
            </div>
           	</form>
        </div>
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
</body><script>
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
		if (pw.value == "") { //비밀번호를 변경하지 않는 경우
			pw.value = "";
            document.getElementById('update').submit();
		}
        if (newMatch) { //비밀번호를 변경하는 경우
            matchCurrentPw.innerHTML = "";
            document.getElementById('update').submit();
        }
    }
</script>
</html>
