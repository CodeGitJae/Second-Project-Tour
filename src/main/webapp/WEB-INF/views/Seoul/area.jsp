<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp" %>

<main id="main">

  <!-- ======= Our Services Section ======= -->
  <section class="breadcrumbs">
    <div class="container">

      <div class="d-flex justify-content-between align-items-center">
        <h2>해당 페이지 문구 넣으면됨</h2>
      </div>

    </div>
  </section><!-- End Our Services Section -->

 <div class="container" style="height:800px">   
  	  <h1>서울 지역 관광 정보</h1>
   <div class="citys"> 
   			<button type="button" class="showAllSigunguBtn" data-sigungu="0">서울</button>
    <c:forEach var="sigunguInfo" items="${sigunguCode}">
    	 	<button type="button" class="sigunguBtn" data-sigungu="${sigunguInfo.code}">${sigunguInfo.name}</button>
    </c:forEach>
   </div> 
	  <div class="article-list-slide mt-4">
	    <ul class="article-list">
	        <c:forEach var="item" items="${tourData}">
	        	<c:choose>
		        	<c:when test="${empty item.firstimage}">
			            <li class="article-item">
			            	<div class="items">
				                <div class="image-box">
		                            <img src="${pageContext.request.contextPath}/assets/img/preparingforimage.png" alt="${item.title}">
				                </div>
				                <div class="text-box">
				                    <h3>${item.title}</h3>
				                    <p>${item.addr1}</p>
				                </div>
			                </div>
			            </li>
		            </c:when>
		            <c:otherwise>
			             <li class="article-item">
				            	<div class="items">
					                <div class="image-box">
			                            <img src="${item.firstimage}" alt="${item.title}">
					                </div>
					                <div class="text-box">
					                    <h3>${item.title}</h3>
					                    <p>${item.addr1}</p>
					                </div>
				                </div>
				            </li>
		            </c:otherwise>
	            </c:choose>
	        </c:forEach>
		</ul>
	</div>
	<div class="pagination">
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		    <li class="page-item">
		    <c:choose>
		    	<c:when test="${curPage <= 1 }">
			      <a class="page-link" href="/Seoul/area?page=1" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
		      	</c:when>
		      	<c:otherwise>
		      		<a class="page-link" href="/Seoul/area?page=${curPage - 1}" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
		      	</c:otherwise>
	      	</c:choose>
		    </li>
		    <c:set var="startPage" value="${curPage - (curPage % 5 == 0 ? 4 : (curPage % 5 - 1))}" />
		    	<c:if test="${startPage < 1}">
                    <c:set var="startPage" value="1" />
              	</c:if>
	        <c:set var="endPage" value="${startPage + 4}" />
	        
	        <c:if test="${endPage > totalPages}">
	            <c:set var="endPage" value="${totalPages}" />
	        </c:if>

		    <c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
				<li class="page-item ${pageNum == curPage ? 'active' : ''}">
					<a class="page-link" href="/Seoul/area?page=${pageNum}">${pageNum}</a>
				</li>
			</c:forEach>
			<c:choose>
				<c:when test="${curPage >= totalPages}">
				    <li class="page-item">
				      <a class="page-link" href="/Seoul/area?page=${totalPages}" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
			    </c:when>
			    <c:otherwise>
			    	<li class="page-item">
				      <a class="page-link" href="/Seoul/area?page=${curPage + 1}" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
			    </c:otherwise>
	    	</c:choose>
		  </ul>
		</nav>
	</div>
</div>
    
</main>

    
<%@ include file="../components/footer.jsp" %>

<script src="${pageContext.request.contextPath}/assets/js/filteredItems.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/filteredPaging.js"></script>
<script>
$(document).ready(function(){
	// 시군구 코드 사용을 위해 전역 변수 선언
	let selectedSigungu = 0;
	
	// ajax 통신으로 페이징 구현
	$(".sigunguBtn").click(function(){
		selectedSigungu = $(this).data('sigungu');
		
	    // 해당 지역 정보를 서버로부터 가져오기
		$.ajax({
			type: 'GET',
			url: `/Seoul/area/`+ selectedSigungu,
			dataType: 'json',
			success: function(responseMap){
				console.log(responseMap);
				var tourData = responseMap.tourData;
/* 		        var totalCount = responseMap.totalCount; */
		        var totalPages = responseMap.totalPages;
		        var curPage = responseMap.curPage;
				
				// 지역 정보에 따른 새로운 페이지 불러오기
				let pageing = changeGuPaging(curPage, totalPages);
				$(".pagination").html(pageing);
				
			},
		});

	});
	
	// 지역 pageNumber 버튼 클릭하면 해당 페이지로 이동 
	$("body").on('click', '.movePage', function(e){
		e.preventDefault();
		
		let page = $(this).text();
		
	    // 특정 지역 페이지 ajax 통신
		$.ajax({
			type: 'GET',
			url: '/Seoul/area/' + selectedSigungu + '?page='+page,
			dataType: 'json',
			success: function(response){
				console.log(response)
				var tourData = response.tourData;
		        var totalPages = response.totalPages;
		        var curPage = response.curPage;
		        
		    	// 지역 버튼 클릭되면 해당 지역만 필터링 후 불러오기
				let items = showItemsByGuArr(tourData);
				$(".article-list").html(items);
				
				// 지역 정보에 따른 새로운 페이지 불러오기
				let pageing = changeGuPaging(curPage, totalPages);
				$(".pagination").html(pageing);
		   
			},
			error: function(xhr, status, error){
				console.error(error);
			}
		});

	});
	
	// 이전 페이지 다음 페이지 버튼 동작 구현 
	$("body").on('click', '.prevPage, .nextPage', function(e){
		e.preventDefault();
		
		let page = $(this).data('page');
		
	    // 특정 지역 페이지 ajax 통신
		$.ajax({
			type: 'GET',
			url: '/Seoul/area/' + selectedSigungu + '?page='+page,
			dataType: 'json',
			success: function(response){
/* 				console.log(response) */
				var tourData = response.tourData;
		        var totalPages = response.totalPages;
		        var curPage = response.curPage;
		        
		    	// 지역 버튼 클릭되면 해당 지역만 필터링 후 불러오기
				let items = showItemsByGuArr(tourData);
				$(".article-list").html(items);
				
				// 지역 정보에 따른 새로운 페이지 불러오기
				let pageing = changeGuPaging(curPage, totalPages);
				$(".pagination").html(pageing);	   
				
			},
			error: function(xhr, status, error){
				console.error(error);
			}
		});

	});
	
	// 서울 버튼 클릭하면 서울 전체 데이터가 보이도록 설정
	$(".showAllSigunguBtn").click(function(){
/* 		let showAllSeoul = $(this).data('sigungu'); */
		
		$.ajax({
			type: 'GET',
			url: '/Seoul/area/all',
			dataType: 'json',
			success: function(selectedAreaArr){
				let items = showItemsByGuArr(selectedAreaArr);
				$(".article-list").html(items);
				
			}
		});
	});
		
	// 지역 버튼 클릭하면 지역 정보만 필터링 되도록 설정
	$(".sigunguBtn").click(function(){
		let selectedSigungu = $(this).data('sigungu');
		
	    // 해당 지역 정보를 서버로부터 가져오기
		$.ajax({
			type: 'GET',
			url: `/Seoul/area/`+ selectedSigungu,
			dataType: 'json',
			success: function(responseMap){
				var tourData = responseMap.tourData;
		        
				// 지역 버튼 클릭되면 해당 지역만 필터링 후 불러오기
				let items = showItemsByGuArr(tourData);
				$(".article-list").html(items);
				
			},
			error: function(xhr, status, error){
				console.error(error);
			}
		});

	});	
	
});
</script>
