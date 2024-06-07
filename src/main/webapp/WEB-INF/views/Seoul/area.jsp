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
    <c:forEach var="sigunguInfo" items="${allTourData}">
    	 	<button type="button" class="sigunguBtn" data-sigungu="${sigunguInfo.sigunguCode}">${sigunguInfo.sigunguName}</button>
    </c:forEach>
   </div> 
	  <div class="article-list-slide mt-4">
	    <ul class="article-list">
		    <c:forEach var="sigunguInfo" items="${allTourData}">
		        <c:forEach var="item" items="${sigunguInfo.tourData}">
		          	 <c:if test="${not empty item.firstimage}">
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
		           	</c:if>
		        </c:forEach>
		    </c:forEach>
		</ul>
	</div>
</div>
    
</main>
    
<%@ include file="../components/footer.jsp" %>

<script src="${pageContext.request.contextPath}/assets/js/filteredItems.js"></script>
<script>
$(document).ready(function(){
	$(".showAllSigunguBtn").click(function(){
/* 		let showAllSeoul = $(this).data('sigungu'); */
		
		$.ajax({
			type: 'GET',
			url: '/Seoul/area/seoul',
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
			success: function(selectedAreaArr){
				let items = showItemsByGuArr(selectedAreaArr);
				$(".article-list").html(items);
				
			},
			error: function(xhr, status, error){
				console.error(error);
			}
		});

	});
});
</script>
