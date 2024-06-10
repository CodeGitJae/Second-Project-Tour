<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp" %>
<link href="${pageContext.request.contextPath}/assets/css/restaurant.css" rel="stylesheet" type="text/css">

<main id="main">

  <!-- ======= Our Services Section ======= -->
<section class="breadcrumbs">
	<div class="container">

		<div class="d-flex justify-content-between align-items-center">
			<img src="/tour/src/main/resources/static/restaurant_img/foodmain_img.jpg" alt="미식의 도시, 서울">
		</div>

	</div>
</section><!-- End Our Services Section -->

<div class="container" style="height:800px">   
	<h5>서울 지역 음식점 정보</h5>
	<div class="citys"> 
		<button type="button" class="allsigunguBtn" data-sigungu="${sigunguInfo}">전체</button>
		<c:forEach var="sigunguInfo" items="${allrestaurantData}">
			<button type="button" class="sigunguBtn" data-sigungu="${sigunguInfo.sigunguCode}">${sigunguInfo.sigunguName}</button>
		</c:forEach>
	</div> 
	<div class="article-list-slide">
    	<ul class="article-list">
	        <c:forEach var="sigunguInfo" items="${allrestaurantData}">
	            <c:forEach var="item" items="${sigunguInfo.restaurantData}">
	            	<!--<c:if test="${not empty item.firstimage || not empty item.firstimage2}">-->
		                <li class="article-item">
		                    <div class="items">
		                        <div class="image-box">
		                            <c:choose>
		                                <c:when test="${not empty item.firstimage}">
		                                    <img src="${item.firstimage}" alt="${item.title}">
		                                </c:when>
		                                <c:otherwise>
		                                    <img src="${item.firstimage2}" alt="${item.title}">
		                                </c:otherwise>
		                            </c:choose>
		                        </div>
		                        <div class="text-box">
		                            <h3>${item.title}</h3>
		                            <p>${item.addr1}</p>
		                        </div>
		                    </div>
		                </li>
	                <!--</c:if>-->
	            </c:forEach>
	        </c:forEach>
		</ul>
	</div>
</div>

<div class="pagination">
    <c:if test="${currentPage > 1}">
        <a href="?pageNo=${currentPage - 1}">&laquo; Previous</a>
    </c:if>
    <span>Page ${currentPage}</span>
    <c:if test="${not empty restaurants && restaurants.size() == 8}">
        <a href="?pageNo=${currentPage + 1}">Next &raquo;</a>
    </c:if>
</div>
</main>
    
<%@ include file="../components/footer.jsp" %>

<script src="${pageContext.request.contextPath}/assets/js/restaurant.js"></script>

<script>
$(document).ready(function(){
	$(".sigunguBtn").click(function(){
		let selectedSigungu = $(this).data('sigungu');
		
		$.ajax({
			type: 'GET',
			url: `/Restaurant/area/`+ selectedSigungu,
			dataType: 'json',
			success: function(arr){
				console.log(arr);
				let items = showItemsByGuArr(arr);
				$(".article-list").html(items);
				
			},
			error: function(xhr, status, error){
				console.error(error);
			}
		});
	});

	$(".allsigunguBtn").click(function(){
		
		location.href="/Restaurant/area"
	});
});
</script>