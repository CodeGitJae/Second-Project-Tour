<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp" %>
<link href="/assets/css/info/tourmap.css" rel="stylesheet">

<main id="main">
  <%@ include file="./subHeader.jsp" %>
  
  
  <div class="container">
  	<div class="map_wrap my-3" style="width:100%;height:550px;">
	    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
	    <ul id="category">
	        <li id="AT4" data-order="0"> 
	            <span class="place"></span>
	            관광명소
	        </li>       
	        <li id="AD5" data-order="1"> 
	            <span class="hotel"></span>
	            숙박
	        </li>  
	        <li id="FD6" data-order="2"> 
	            <span class="food"></span>
	            음식점
	        </li>
	        <li id="CE7" data-order="3"> 
	            <span class="cafe"></span>
	            카페
	        </li>     
	    </ul>
	</div>
  </div>
    
</main>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2ad95b349288a2f15e7c503c543bb5fe&libraries=services"></script>
<script src="/assets/js/info/tourmap.js"></script>
<%@ include file="../components/footer.jsp" %>