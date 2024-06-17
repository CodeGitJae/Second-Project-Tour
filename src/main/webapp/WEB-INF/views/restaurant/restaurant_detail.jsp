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

		<c:forEach var="rtrInfo" items="${rtrInfoList}">
			<input id="rtrmap" type="hidden" data-lon="${rtrInfo.lon}"
				data-lat="${rtrInfo.lat}" value="location">
			<div class="rtrItem" style="width: 18rem;">
				<img src="${rtrInfo.firstimage}" class="ftv-img"
					alt="${rtrInfo.title}">
				<div class="ftv-details">
					<p><strong>맛집명:</strong> ${rtrInfo.title}</p>
					<p><strong>주소:</strong> ${rtrInfo.addr1}+${rtrInfo.addr2}</p>
					
					
					<p><strong>전화번호:</strong> ${rtrInfo.tel}</p>
					<p><strong>추천메뉴:</strong> ${rtrInfo.firstmenu}</p>
					<p><strong>판매메뉴:</strong> ${rtrInfo.treatmenu}</p>
					<p><strong>휴일:</strong> ${rtrInfo.dayoff}</p>
					<p><strong>휴일:</strong> ${rtrInfo.dayoff}</p>
					<p><strong>예약:</strong> ${rtrInfo.reservation}</p>
					<p><strong>주차:</strong> ${rtrInfo.parkingd}</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="map" style="width: 500px; height: 400px;"></div>
</main>

<%@ include file="../components/footer.jsp"%>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6eea508bf791ab03da7e03665b29880c"></script>
<script>
	$(document).ready(function() {

		var lon = $("#rtrmap").data('lon');
		cC
		var lat = $("#rtrmap").data('lat');

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