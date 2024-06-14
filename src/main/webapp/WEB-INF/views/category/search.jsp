<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp" %>
<link href="${pageContext.request.contextPath}/assets/css/recommendStyle.css" rel="stylesheet" type="text/css">
<main id="main">

  <!-- ======= Our Services Section ======= -->
  <section class="breadcrumbs">
    <div class="container">

      <div class="d-flex justify-content-between align-items-center">
        <h2 style="font-size: 30px; font-weight: bold; color: #999; text-align: center;">${selectedName} 추천 장소</h2>
        <div class="category">
	        <a class="tag-link" href="/category/travel?contentTypeId=14">문화시설</a>
	        <a class="tag-link" href="/category/travel?contentTypeId=32">숙박</a>
	        <a class="tag-link" href="/category/travel?contentTypeId=38">쇼핑</a>
	        <a class="tag-link" href="/category/travel?contentTypeId=25">여행코스</a>
	        <a class="tag-link" href="/category/travel?contentTypeId=28"> 레포츠</a>
        </div>        
      </div>

    </div>
  </section><!-- End Our Services Section -->

 <div class="container">
 		<div class="mt-3" style="margin-left: 160px;">
 			<form action="/category/search" method="get">
			    <label for="search" class="form-label"></label>
			    <input type="hidden" name="contentTypeId" value="${searchKeyword[0].contenttypeid}">  <!-- 인덱스 0번째는 읽어오는 객체 숫자의 인덱스 번호임 -->
			    <input style="width: 20%; height: 40px;" id="search" name="search" placeholder="검색어를 입력하세요.">
			     <button type="submit" class="btn btn-primary">검색</button>
	  		</form>
	  	</div>
	  <div class="article-list-slide mt-4">   <!-- 카테고리 항목 클릭하면 서울 관광지 보여주는 div -->
	    <ul class="article-list">
	        <c:forEach var="item" items="${searchKeyword}">
	        	<c:choose>
		        	<c:when test="${empty item.firstimage}">
			            <li class="article-item">
			            	<div class="items">
				                <div class="image-box">
		                           <a href="/category/searchdetail?search=${keyword}&contentTypeId=${item.contenttypeid}&contentId=${item.contentid}">  <!-- 클릭 시 상세보기로 이동 -->
		                            <img src="${pageContext.request.contextPath}/assets/img/preparingforimage.png" alt="${item.title}">
				                   </a>
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
					                  <a href="/category/searchdetail?search=${keyword}&contentTypeId=${item.contenttypeid}&contentId=${item.contentid}"> <!-- 클릭 시 상세보기로 이동 -->
			                            <img src="${item.firstimage}" alt="${item.title}">
					                  </a>
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
	<div class="pagination">      <!-- 서울 지역 페이지 네이션 구현 / 지역벌 페이지는 ajax로 구현함-->
		<nav aria-label="Page navigation example">
		  <ul class="pagination">
		    <li class="page-item">
    		    <a class="page-link" href="/category/search?search=${keyword}&contentTypeId=${searchKeyword[0].contenttypeid}&page=1" aria-label="Previous">
			      <span aria-hidden="true">&laquo;</span>
		        </a>
		    <c:choose>
		    	<c:when test="${curPage - 5 < 1}">
			      <li class="page-item"><a class="page-link" href="/category/search?search=${keyword}&contentTypeId=${searchKeyword[0].contenttypeid}&page=1">이전</a></li>
		      	</c:when>
		      	<c:otherwise>
		      		<li class="page-item"><a class="page-link" href="/category/search?search=${keyword}&contentTypeId=${searchKeyword[0].contenttypeid}&page=${curPage - 5}">이전</a></li>
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
					<a class="page-link" href="/category/search?search=${keyword}&contentTypeId=${searchKeyword[0].contenttypeid}&page=${pageNum}">${pageNum}</a>
				</li>
			</c:forEach>
			<c:choose>
				<c:when test="${curPage + 5 >= totalPages}">
					<li class="page-item"><a class="page-link" href="/category/search?search=${keyword}&contentTypeId=${searchKeyword[0].contenttypeid}&page=${totalPages}">다음</a></li>
			    </c:when>
			    <c:otherwise>
			    	<li class="page-item"><a class="page-link" href="/category/search?search=${keyword}&contentTypeId=${searchKeyword[0].contenttypeid}&page=${curPage + 5}">다음</a></li>
			    </c:otherwise>
	    	</c:choose>
    	   	 <li class="page-item">
		      <a class="page-link" href="/category/search?search=${keyword}&contentTypeId=${searchKeyword[0].contenttypeid}&page=${totalPages}" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
	</div>
</div>
    
</main>

    
<%@ include file="../components/footer.jsp" %>

<script>
//$(document).ready(function(){
const contentType = `${searchKeyword}`;
console.log(contentType);
</script>
