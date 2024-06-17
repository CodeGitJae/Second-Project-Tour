<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Seoul Tour</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="${pageContext.request.contextPath}/assets/img/favicon.png" rel="icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,700,700i&display=swap" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${pageContext.request.contextPath}/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
  
  <%
    WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
    String weatherApikey = (String) context.getBean("weatherApikey");
  %>
  <!-- open weather js -->
  <script type="text/javascript">
    const weatherApikey = '<%= weatherApikey %>';
  </script>
  <script src="${pageContext.request.contextPath}/assets/js/weather.js"></script>

  <!-- =======================================================
  * Template Name: Moderna
  * Template URL: https://bootstrapmade.com/free-bootstrap-template-corporate-moderna/
  * Updated: May 7 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top align-items-center header-transparent">
    <div id="google_translate_element" style="height: 40px;"></div>
    <div class="container d-flex justify-content-between align-items-center">

      <div class="logo">
        <h1 class="text-light"><a href="/"><span>Tour</span></a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
      </div>

      <nav id="navbar" class="navbar">
        <ul>
          <li><a href="/">Home</a></li>
          <li><a href="">지역</a></li>
          <li><a href="">여행지</a></li>
          <li><a href="">맛집</a></li>
          <li><a href="">축제</a></li>
          <li><a href="/info/traffic">여행정보</a></li>
        </ul>
      </nav><!-- .navbar -->
      
      <div class="weather">
        <span>
          <img class="icon" src="">
        </span>
        <span class="temp"></span>
      </div>

      <i class="bi bi-list mobile-nav-toggle"></i>

    </div>
  </header><!-- End Header -->
<!-- Google Translate script -->
<script type="text/javascript">
  function googleTranslateElementInit() {
      new google.translate.TranslateElement({
          pageLanguage: 'ko', // 원본 페이지 언어를 지정합니다.
          includedLanguages: 'en,ja,zh-CN,es,fr,it', // 포함할 언어를 지정합니다.
          layout: google.translate.TranslateElement.InlineLayout.SIMPLE
      }, 'google_translate_element');
  }
</script>
<script type="text/javascript" src="https://translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>