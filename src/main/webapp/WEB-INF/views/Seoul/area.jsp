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
		      <a class="page-link" href="/Seoul/area?page=${curPage - 1}" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
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
		     	<c:choose>
		     		<c:when test="${pageNum ==curPage}">
		    			<strong>
		    				<li class="page-item">
		    					<a class="page-link" href="/Seoul/area?page=${pageNum}" style="color: red;">
		    						${pageNum}
		    					</a>
		    				</li>
						</strong>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="/Seoul/area?page=${pageNum}">${pageNum}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		    <li class="page-item">
		      <a class="page-link" href="/Seoul/area?page=${curPage + 1}" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
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
		
	$(".sigunguBtn").click(function(){
		let selectedSigungu = $(this).data('sigungu');
		
	    // 해당 지역 정보를 서버로부터 가져오기
		$.ajax({
			type: 'GET',
			url: `/Seoul/area/`+ selectedSigungu,
			dataType: 'json',
			success: function(responseMap){
				console.log(responseMap);
				var tourData = response.tourData;
		        var totalCount = response.totalCount;
		        var totalPages = response.totalPages;
		        var curpage = response.curpage;

				let items = showItemsByGuArr(responseMap.tourData);
				$(".article-list").html(items);
				
			},
			error: function(xhr, status, error){
				console.error(error);
			}
		});

	});
});
</script>
