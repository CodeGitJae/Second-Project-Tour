<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp"%>

<main id="main">

	<!-- ======= Our Services Section ======= -->
	<section class="breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h2>${ftvInfo.title}</h2>
			</div>

		</div>
	</section>
	<!-- End Our Services Section -->

	<div class="container">

		<c:forEach var="ftvInfo" items="${ftvInfoList}">
			<input id="ftvmap" type="hidden" data-lon="${ftvInfo.lon}" data-lat="${ftvInfo.lat}" value="location">
			<div class="ftvItem" style="width: 18rem;">
				<img src="${ftvInfo.firstimage}" class="ftv-img" alt="${ftvInfo.title}">
				<div class="ftv-details">
					<p><strong>축제명:</strong> ${ftvInfo.title}</p>
					<p><strong>주소:</strong> ${ftvInfo.addr1}+${ftvInfo.addr2}</p>
					<p><strong>전화번호:</strong> ${ftvInfo.telname} : ${ftvInfo.tel}</p>
					<p><strong>홈페이지:</strong> ${ftvInfo.homepage}</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="map" style="width:500px;height:400px;"></div>
</main>
		
<%@ include file="../components/footer.jsp"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6eea508bf791ab03da7e03665b29880c"></script>
<script>
$(document).ready(function(){	

	var lon = $("#ftvmap").data('lon');cC
	var lat = $("#ftvmap").data('lat');

	console.log(lon, lat);
	var container = document.getElementById('map');
	var options = {
		center : new kakao.maps.LatLng(lat, lon),
		level : 2
	};

	var map = new kakao.maps.Map(container, options);

	// 마커가 표시될 위치입니다 
	var markerPosition = new kakao.maps.LatLng(lat, lon);

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
		position : markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
	
});
</script>