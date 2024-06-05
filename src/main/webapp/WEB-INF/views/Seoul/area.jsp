<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp" %>

<style>
.citys {
    display: flex;
    flex-wrap: wrap;
}
.citys button {
	padding: 10px;
	margin: 2px;
    margin-bottom: 10px;
}
    
.article-list-slide {
    display: flex;
    overflow-x: auto;
}

.article-list {
    display: flex;
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.article-item {
    margin-right: 20px;
}

.image-box {
    width: 300px;
    height: 200px;
    overflow: hidden;
    border: 1px solid #ddd; /* 테두리 스타일링 */
    border-radius: 5px; /* 사각형 박스의 모서리 둥글게 만듦 */
}

.image-box img {
    width: 100%;
    height: auto;
    display: block;
}

.text-box {
    padding: 10px;
    background-color: #f9f9f9; /* 텍스트 박스 배경색 */
}

.text-box h3 {
    margin: 0;
    font-size: 18px;
    font-weight: bold;
}

.text-box p {
    margin: 5px 0;
    font-size: 14px;
    color: #666;
}
</style>
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
  	  <h1>Seoul Area Tour Data</h1>
   <div class="citys"> 
    <c:forEach var="sigunguInfo" items="${allTourData}">
    	 	<button type="button">${sigunguInfo.sigunguName}</button>
    </c:forEach>
   </div> 
	  <div class="article-list-slide">
	    <ul class="article-list">
	        <c:forEach var="item" items="${allTourData}">
	            <li class="article-item">
	                <div class="image-box">
		                <c:choose>
		                	<c:when test="${empty item.firstimage}">
		                    	<img src="${item.firstimage2}" alt="${item.title}">
		                	</c:when>
		                	<c:otherwise>
		                		<img src="${item.firstimage}" alt="${item.title}">
		                	</c:otherwise>
	                	</c:choose>
	                </div>
	                <div class="text-box">
	                    <h3>${item.sigunguInfo.tourData.title}</h3>
	                    <p>${item.sigunguInfo.tourData.addr1}</p>
	                </div>
	            </li>
	        </c:forEach>
	    </ul>
	</div>
  </div>
    
</main>
    
<%@ include file="../components/footer.jsp" %>