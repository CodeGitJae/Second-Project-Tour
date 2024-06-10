<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../components/header.jsp" %>

<main id="main">
  <%@ include file="./subHeader.jsp" %>

  <div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10 text-center">
            <h1 class="my-4">서울 날씨</h1>
            <div class="card forecast-card">
                <div class="card-body">
                    <h4>오늘 시간대별 날씨</h4>
                    <div class="row" id="hourly-forecast"></div>
                </div>
            </div>
            <div class="card forecast-card my-3">
                <div class="card-body">
                    <h4>5일간 날씨</h4>
                    <div class="row" id="daily-forecast"></div>
                </div>
            </div>
        </div>
    </div>
  </div>
    
</main>

<script src="/assets/js/info/seoulWeather.js"></script>
<%@ include file="../components/footer.jsp" %>