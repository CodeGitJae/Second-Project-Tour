<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp" %>
<link href="${pageContext.request.contextPath}/assets/css/detailStyle.css" rel="stylesheet" type="text/css">

<main id="main">

  <!-- ======= Our Services Section ======= -->
  <section class="breadcrumbs">
    <div class="container">

      <div class="d-flex justify-content-between align-items-center">
        <h2>관광지 상세정보</h2>
      </div>

    </div>
  </section><!-- End Our Services Section -->

  <div class="container">
   	<c:forEach items="${detailData}" var="item"> 
   		<input id="location" type="hidden" data-lon="${item.lon}" data-lat="${item.lat}" value="location">
	  	<div class="card mb-3">
	  	 <c:choose>
	  	 	<c:when test="${empty item.firstimage}">
  		 		 <img style="max-height: 750px;" src="${pageContext.request.contextPath}/assets/img/preparingforimage.png" alt="${item.title}">
  		 	</c:when>
  		 	<c:otherwise>
  		 		<img src="${item.firstimage}" class="card-img-topt" alt="${item.title}">
  		 	</c:otherwise>
  		 </c:choose>
	  	  <div class="card-body">
		    <h5 class="card-title">장소명 [${item.title}]</h5>
		    <p class="card-text">${item.overview}</p>
		    <p class="card-sec-text">전화번호 | ${item.tel}</p>  
   		    <p class="card-sec-text">주소지　| ${item.addr1}</p>
   		    <p class="card-sec-text">홈페이지 | ${item.homepage}</p>
		    <button class="btn btn-primary" id="goBackBtn" >뒤로가기</button>
		  </div>
		</div>
 	</c:forEach> 
  </div>
 <div id="map" class="map"></div>
    
</main>
    
<%@ include file="../components/footer.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=88fb446516093302610e307ca5917ea6"></script>
<script>
$(document).ready(function(){
	$("#goBackBtn").click(function(){
		history.back();  //뒤로가기 버튼 구현  !!!추후 데이터 바인딩해서 전 페이지 데이터 담아서 구현하는 방향으로 바꿔야함.!!!
	});
	
	var lon = $("#location").data('lon');
	var lat = $("#location").data('lat');
	
	var container = document.getElementById('map');
	var options = {
		center: new kakao.maps.LatLng(lat, lon),
		level: 2
	};
	
	var map = new kakao.maps.Map(container, options);
	
	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(lat, lon); 

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
});
</script>