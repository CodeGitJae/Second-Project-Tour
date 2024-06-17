<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp"%>
<!-- 부트스트랩 CSS 추가 -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/restaurant.css"
	rel="stylesheet" type="text/css">
	

<main id="main">

	<!-- ======= Our Services Section ======= -->
	<section class="breadcrumbs">
		<div class="container">

			<div class="d-flex justify-content-between align-items-center">
				<h1>미식의 도시, 서울</h1>
			</div>

		</div>
	</section>
	<!-- End Our Services Section -->

	<div class="container" style="height: 800px">
		<h5>서울 지역 음식점 정보</h5>
		
		<!-- 도시별 태그버튼 -->
		<div class="citys mb-4">
			<button type="button" class=" btn btn-primary allsigunguBtn">전체</button>
			<c:forEach var="sigunguInfo" items="${allrestaurantData}">
				<button type="button" class="btn btn-outline-primary sigunguBtn"
					data-sigungu="${sigunguInfo.sigunguCode}">${sigunguInfo.sigunguName}</button>
			</c:forEach>
		</div>
		
		<!-- 데이터 로드 -->
		<div class="article-list-slide">
			<ul class="article-list list-unstyled row">
				<c:forEach var="sigunguInfo" items="${allrestaurantData}">
					<c:forEach var="rtritem" items="${sigunguInfo.restaurantData}">
						<c:if
							test="${not empty rtritem.firstimage || not empty rtritem.firstimage2}">
							
							<li class="article-item col-md-4 mb-4">
								<div class="card h-100">
									<div class="image-box">
										<c:choose>
											<c:when test="${not empty rtritem.firstimage}">
												<img src="${rtritem.firstimage}" class="card-img-top"
													alt="${rtritem.title}">
											</c:when>
											<c:otherwise>
												<img src="${rtritem.firstimage2}" class="card-img-top"
													alt="${rtritem.title}">
											</c:otherwise>
										</c:choose>
									</div>
									<div class="card-body">
										<h5 class="card-title">${rtritem.title}</h5>
										<p class="card-text">${rtritem.addr1}</p>
										<a class="rtr btn-primary" data-contentid="${rtritem.contentid}">상세보기</a>
									</div>
								</div>
							</li>
							
						</c:if>
					</c:forEach>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="pagination">
		<c:if test="${currentPage > 1}">
			<a href="?pageNo=${currentPage - 1}&pageSize=${pageSize}"
				class="btn btn-secondary">&laquo; Previous</a>
		</c:if>
		<span class="mx-2">Page ${currentPage}</span>
		<c:if
			test="${not empty allrestaurantData && allrestaurantData.size() == pageSize}">
			<a href="?pageNo=${currentPage + 1}&pageSize=${pageSize}"
				class="btn btn-secondary">Next &raquo;</a>
		</c:if>
	</div>

</main>

<%@ include file="../components/footer.jsp"%>

<!-- 부트스트랩 JS 추가 -->
 <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXlL6joIu6pP3Ez2nPajp4ysqKHX1p6K7AKHpZ1qRjb0QmOYjHYjE/+1A6Rm"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgEP4J5ApLA7ECg0Rlyp2qE4t5F2TfLkk7QK5G86UItlft4U58R"
	crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/assets/js/restaurant.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
$(document).ready(function(){
	//도시별 태그버튼
	$(".sigunguBtn").click(function(){
        let selectedSigungu = $(this).data('sigungu');
        let pageNo = 1; // 페이지 번호를 정의합니다.
        let pageSize = 8; // 페이지 크기를 정의합니다.
		
		$.ajax({
			type: 'GET',
			url: `/restaurant/area/`+ selectedSigungu,
			data: {
                pageNo: pageNo,
                pageSize: pageSize
            },
			dataType: 'json',
			success: function(arr){
				console.log(arr);
				let items = showItemsByGuArr(arr);
				$(".article-list").html(items);
				updatePagination(selectedSigungu, pageNo, pageSize);
			},
			error: function(xhr, status, error){
				console.error(error);
			}
		});
	});
	

	//전체도시 버튼 클릭이벤트
	$(".allsigunguBtn").click(function(){
		location.href="/restaurant/area"
	});
	
	
	//상세페이지로 이동
	$(".rtr").on("click", function() {
		var contentid = $(this).data("contentid");
		console.log(contentid);

		location.href = '/restaurant/detail?contentid=' + contentid
	});
});
</script>
