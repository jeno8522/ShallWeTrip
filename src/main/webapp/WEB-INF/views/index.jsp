<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/common/head.jsp" %>
</head>
<body>
	<!-- ******HEADER****** --> 
    <header id="header" class="header">  
    	<%@ include file="/WEB-INF/views/common/nav.jsp" %>
    </header><!--//header-->
    
    <div id="hero" class="hero-section">
        
        <div id="hero-carousel" class="hero-carousel carousel carousel-fade slide" data-bs-ride="carousel" data-bs-interval="10000">
            
            <div class="figure-holder-wrapper">
        		<div class="container">
            		<div class="row justify-content-end">

            		</div><!--//row-->
        		</div><!--//container-->
    		</div><!--//figure-holder-wrapper-->
            
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li class="active" data-bs-slide-to="0" data-bs-target="#hero-carousel"></li>
				<li data-bs-slide-to="1" data-bs-target="#hero-carousel"></li>
				<li data-bs-slide-to="2" data-bs-target="#hero-carousel"></li>
                <li data-bs-slide-to="3" data-bs-target="#hero-carousel"></li>
			</ol>
			
			<!-- Wrapper for slides -->
			<div class="carousel-inner">
    			
				<div class="carousel-item item-1 active">
					<div class="item-content container">
    					<div class="item-content-inner mt-5">
    				        
				            <h1 class="heading">ENJOYTRIP</h1>
    				        
    					</div><!--//item-content-inner-->
					</div><!--//item-content-->
				</div><!--//item-->
				
				<div class="carousel-item item-2">
					<div class="item-content container">
						<div class="item-content-inner">
    				        
				                					</div><!--//item-content-inner-->
					</div>
				</div><!--//item-->
				
				<div class="carousel-item item-3">
					<div class="item-content container">
						<div class="item-content-inner">
    				        
				            
    					</div><!--//item-content-inner-->
					</div>
				</div><!--//item-->
                <div class="carousel-item item-4">
					<div class="item-content container">
						<div class="item-content-inner">
    				        
				            
    					</div><!--//item-content-inner-->
					</div>
				</div><!--//item-->
			</div><!--//carousel-inner-->

		</div><!--//carousel-->
    </div><!--//hero-->
    
    <div class="contents">
        <div class ="container">
            
            <div class="function-button mb-5">
                <a href="${root }/attraction?action=tripInfo">
                    <img src="/trip/assets/images/main-bt-001.jpg" alt="" class="function-image">
                </a>
                
                <div class="function-text" id="text1">지역별 관광지 조회</div>
            </div>
            
            <div class="function-button mb-5">
                <a href="${root }/article?action=list">
                    <img src="/trip/assets/images/main-bt-002.jpg" alt="" class="function-image">
                </a>
                <div class="function-text" id="text2">여행 계획</div>
            </div>
            <div class="function-button mb-5">
                <a href="${root }/trip/tripPlan.jsp">
                    <img src="/trip/assets/images/main-bt-003.jpg" alt="" class="function-image">
                </a>
                <div class="function-text" id="text3">인기 관광지</div>
            </div>
        </div>
    </div><!--//about-section-->
    
    
    
    <footer class="footer text-center">
        <div class="container row">
            <div class="col-lg-4">
                <!-- <img src="/trip/assets/images/footer-logo.jpg" alt="" class="h-100"> -->
            </div>
        </div><!--//container-->
    </footer>
     
    <!-- Javascript -->          
    <script src="/trip/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="/trip/assets/plugins/gumshoe/gumshoe.polyfills.min.js"></script> 
    <script src="/trip/assets/js/main.js"></script> 
       
</body>
</html> 