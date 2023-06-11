<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:if test='${!empty param.msg}'>
	<c:set var='msg' value="${param.msg}"/>
</c:if>
<meta charset="UTF-8">
<title>EnjoyTrip</title>
<%-- 부트스트랩 사용을 위한 코드 --%>
<!-- CSS only -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico">  
<link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<!-- FontAwesome JS-->
<script defer src="/trip/assets/fontawesome/js/all.min.js"></script>
<!-- Global CSS -->
<link rel="stylesheet" href="/trip/assets/plugins/bootstrap/css/bootstrap.min.css">   
<!-- Theme CSS -->  
<link id="theme-style" rel="stylesheet" href="/trip/assets/css/main.css">
<script>
    <%-- request 객체에 msg가 들어있을 때 해당 내용 알림창 띄우기 --%>
    <c:if test="${!empty msg}">
	alert("${msg}");
	</c:if>
</script>