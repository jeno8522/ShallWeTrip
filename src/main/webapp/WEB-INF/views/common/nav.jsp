<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="main-nav navbar-expand-md" role="navigation">  
       
        <div class="container-fluid position-relative">
           
               <a class="logo navbar-brand scrollto" href="${root}/index">
                   <span class="text ms-3"><span class="highlight">ENJOY</span>TRIP</span>
               </a>
               <!--//logo-->
    
        
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-collapse"  aria-controls="navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button><!--//nav-toggle-->
        
        <div id="navbar-collapse" class="navbar-collapse collapse">
            <ul class="nav navbar-nav ms-md-auto">
                <li class="nav-item"><a class="nav-link scrollto" href="${root }/attraction/tripInfo">관광지 조회</a></li>
                <li class="nav-item"><a class="nav-link scrollto" href="${root }/hotPlace/hotList">핫플</a></li>
                <li class="nav-item"><a class="nav-link scrollto" href="${root }/plan?action=list">여행 계획</a></li>
                <li class="nav-item"><a class="nav-link scrollto" href="${root }/info/infoList">여행 정보 게시판</a></li>
		        <c:choose>
					<c:when test="${empty userInfo}">
					<%-- session에 userInfo 객체 없는 경우(로그인 X) --%>
						<li class="nav-item"><a class="nav-link"
							href="${root}/user/login">로그인</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link"
							href="${root}/user/mypage">MyPage</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="${root}/user/logout">로그아웃</a>
						</li>
					</c:otherwise>
				</c:choose>
            </ul><!--//nav-->
        </div><!--//navabr-collapse-->
    </div><!--//container-->
    
</nav><!--//main-nav-->        