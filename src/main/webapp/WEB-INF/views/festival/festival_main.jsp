<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp"%>
<link href="${pageContext.request.contextPath}/assets/css/festival.css"
	rel="stylesheet" type="text/css">

<main id="main">

	<!-- ======= Our Services Section ======= -->
	<section class="ftv-breadcrumbs">
		<div class="ftv-container">
			<!-- class="d-flex justify-content-between align-items-center" -->
			<div class="ftv-header">
				<h2>익사이팅한, 서울</h2>
			</div>
		</div>
	</section>
	<!-- End Our Services Section -->

	<div class="container">
		<div class="search_box_wrap">
			<!-- 셀렉트박스 -->
			<div class="select_Month">
				<select name="searchMonth" id="searchMonth" title="월별">
					<option ${searchMonth == '' ? 'selected' : ''} value="">월별</option>
					<option ${searchMonth == '1' ? 'selected' : ''} value="01">01월</option>
					<option ${searchMonth == '2' ? 'selected' : ''} value="02">02월</option>
					<option ${searchMonth == '3' ? 'selected' : ''} value="03">03월</option>
					<option ${searchMonth == '4' ? 'selected' : ''} value="04">04월</option>
					<option ${searchMonth == '5' ? 'selected' : ''} value="05">05월</option>
					<option ${searchMonth == '6' ? 'selected' : ''} value="06">06월</option>
					<option ${searchMonth == '7' ? 'selected' : ''} value="07">07월</option>
					<option ${searchMonth == '8' ? 'selected' : ''} value="08">08월</option>
					<option ${searchMonth == '9' ? 'selected' : ''} value="09">09월</option>
					<option ${searchMonth == '10' ? 'selected' : ''} value="10">10월</option>
					<option ${searchMonth == '11' ? 'selected' : ''} value="11">11월</option>
					<option ${searchMonth == '12' ? 'selected' : ''} value="12">12월</option>
				</select>
			</div>

			<div class="btn_box">
				<button class="btn green rounded" id="btnReset">
					<span>초기화</span>
				</button>
			</div>
		</div>

		<!-- 데이터 로드 -->
		<div class="ftv-list-slide">
			<c:forEach var="ftv" items="${ftvList}">
				<c:if test="${ftv.areacode == 1}">
					<div class="ftvItem" style="width: 18rem;">
						<img src="${ftv.firstimage}" class="ftv-img" alt="${ftv.title}">
						<div class="ftv-details">
							<strong>${ftv.title}</strong>
							<div class="ftv-info">${ftv.addr1}</div>
							<div class="ftv-date">${ftv.eventstartdate}~
								${ftv.eventenddate}</div>
							<a class="ftv btn btn-primary" data-contentid="${ftv.contentid}">상세보기</a>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>

		<nav aria-label="Page navigation example">
			<ul class="pagination">
				<!-- 첫 번째 페이지로 이동하는 링크 -->
				<li class="page-item"><a class="page-link"
					href="/festival/festivalMain?page=1" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<!-- 이전 5 페이지 또는 1페이지부터 시작 -->
				<c:choose>
					<c:when test="${curPage - 5 < 1}">
						<li class="page-item"><a class="page-link"
							href="/festival/festivalMain?page=1">이전</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/festival/festivalMain?page=${curPage - 5}">이전</a></li>
					</c:otherwise>
				</c:choose>

				<!-- 페이지네이션 블록의 시작 페이지와 끝 페이지 계산 -->
				<c:set var="startPage"
					value="${curPage - (curPage % 5 == 0 ? 4 : (curPage % 5 - 1))}" />
				<c:if test="${startPage < 1}">
					<c:set var="startPage" value="1" />
				</c:if>
				<c:set var="endPage" value="${startPage + 4}" />
				<c:if test="${endPage > pages}">
					<c:set var="endPage" value="${pages}" />
				</c:if>

				<!-- 페이지를 반복하며 페이지 링크 생성 -->
				<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
					<li class="page-item ${pageNum == curPage ? 'active' : ''}"><a
						class="page-link" href="/festival/festivalMain?page=${pageNum}&searchMonth=${searchMonth}">${pageNum}</a>
					</li>
				</c:forEach>

				<!-- 다음 5 페이지 또는 마지막 페이지로 이동 -->
				<c:choose>
					<c:when test="${curPage + 5 >= pages}">
						<li class="page-item"><a class="page-link"
							href="/festival/festivalMain?page=${pages}">다음</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/festival/festivalMain?page=${curPage + 5}">다음</a></li>
					</c:otherwise>
				</c:choose>

				<!-- 마지막 페이지로 이동하는 링크 -->
				<li class="page-item"><a class="page-link"
					href="/festival/festivalMain?page=${pages}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>


	</div>
</main>

<%@ include file="../components/footer.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script onchange="filterMonth()" name="searchMonth" id="searchMonth"
	title="월별" type="text/javascript">
	$(document).ready(function() {
		//셀렉트박스
		$("#searchMonth").on("change", function(e) {
			let monthTarget = e.target.value;

			location.href = '/festival/festivalMain?searchMonth=' + monthTarget

		});

		//상세페이지로 이동
		$(".ftv").on("click", function() {
			var contentid = $(this).data("contentid");
			console.log(contentid);

			location.href = '/festival/detail?contentid=' + contentid
		});
	});

	//검색 초기화
	function resetSearch() {
		fetchData(); // 데이터 초기화를 위해 다시 데이터를 불러옴
	}
</script>
