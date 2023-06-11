<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div id="body-content" class="info-section">
        <div class="container text-center">
            <div class="fs-2 search-title">지역별 관광지 조회</div>          
            <!-- 관광지 검색 start -->
            <form class="d-flex my-3 mx-auto selection-form" id="search-form" action="${root }/attraction/tripInfo" method="post" role="search">
                <select id="search-area" class="form-select me-2" name="sido">
                    <option value="0" selected>시도</option>
                </select>
                <select id="search-city" class="form-select me-2" name="gugun">
                    <option value="0" selected>구군</option>
                </select>
                <select id="search-content-id" class="form-select me-2" name="contentTypeId">
                    <option value="0" selected>관광지 유형</option>
                    <option value="12">관광지</option>
                    <option value="14">문화시설</option>
                    <option value="15">축제공연행사</option>
                    <option value="25">여행코스</option>
                    <option value="28">레포츠</option>
                    <option value="32">숙박</option>
                    <option value="38">쇼핑</option>
                    <option value="39">음식점</option>
                </select>
                <input
                    id="search-keyword"
                    name="keyword"
                    class="form-control me-2"
                    type="search"
                    placeholder="검색어"
                    aria-label="검색어"
                />
            </form>
            <a id="btn-search" class="btn btn-primary" type="button" 
            	onclick="document.getElementById('search-form').submit()">
            	검색
            </a>
            <div class="row">
                <!-- kakao map start -->
                <div id="map" class="mt-3 mb-3 col-sm-12 col-md-6" style="height: 500px"></div>
                <!-- kakao map end -->
                <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a81de39e6a97a34c20513e24222be640"></script>
                <script src = "${root }/assets/js/tripInfo.js"></script>
                <div class="table-responsive mt-3 mb-3 col-sm-12 col-md-6">
                    <table class="table table-striped" style="display: none">
                        <thead>
                        <tr>
                            <th>대표이미지</th>
                            <th>관광지명</th>
                            <th>주소</th>
                            <th>전화번호</th>
                        </tr>
                        </thead>
                        <tbody id="trip-list">
                            <c:forEach items="${attractions}" var="attraction">
                                <tr onclick="moveCenter(${attraction.latitude}, ${attraction.longitude});" class="align-middle">
                                    <td><img src="${attraction.image}" width="100px"></td>
                                    <td>${attraction.title}</td>
                                    <td>${attraction.addr1} ${attraction.addr2}</td>
                                    <td>${attraction.tel}</td>
                                </tr>
                                <script>
                                addMarker(${attraction.latitude}, ${attraction.longitude}, 
                                        "${attraction.contentTypeId}", "${attraction.title}",
                                        "${attraction.addr1}", "${attraction.addr2}",
                                        "${attraction.image}", "${attraction.tel}");
                                </script>
                            </c:forEach>
                            
                        </tbody>
                    </table>
                </div>
                <!-- 관광지 검색 end -->
            </div>
        </div><!--//container-->
    </div>
    
     <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
</body>
 <!-- Javascript -->          
    <script src="${root }/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="${root }/assets/plugins/gumshoe/gumshoe.polyfills.min.js"></script> 
    <script src="${root }/assets/js/main.js"></script> 
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    
</html>
<script>
<c:if test="${!empty attractions}">
	document.querySelector("table").setAttribute("style", "display: ;");
	setBounds();
</c:if>

</script>