<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="./components/header.jsp" %>

  <!-- ======= Hero Section ======= -->
  <section id="hero" class="d-flex justify-cntent-center align-items-center">
    <div id="heroCarousel" class="carousel slide" data-bs-ride="carousel" data-bs-interval="5000">

      <!-- Slide 1 -->
      <div class="carousel-item active">
        <div class="carousel-container">
          <img src="assets/img/carousel/1.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h2 class="animate__animated animate__fadeInDown">서울시 축제</h2>
            <p class="animate__animated animate__fadeInUp">서울시에서 진행하는 다양한 축제,공연 및 행사 정보들을 찾아 보실 수 있습니다.</p>
            <a href="/festival/festivalMain" class="btn-get-started animate__animated animate__fadeInUp">더보기</a>
          </div>
        </div>
      </div>

      <!-- Slide 2 -->
      <div class="carousel-item">
        <div class="carousel-container">
          <img src="assets/img/carousel/2.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h2 class="animate__animated animate__fadeInDown">서울시 관광지</h2>
            <p class="animate__animated animate__fadeInUp">서울시에 있는 다양한 관광지에 대한 정보들을 찾아 보실 수 있습니다.</p>
            <a href="/Seoul/area" class="btn-get-started animate__animated animate__fadeInUp">더보기</a>
          </div>
        </div>
      </div>

      <!-- Slide 3 -->
      <div class="carousel-item">
        <div class="carousel-container">
          <img src="assets/img/carousel/3.jpg" class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h2 class="animate__animated animate__fadeInDown">서울시 맛집</h2>
            <p class="animate__animated animate__fadeInUp">서울시에 있는 맛집 정보들을 찾아 보실 수 있습니다.</p>
            <a href="/restaurant/area" class="btn-get-started animate__animated animate__fadeInUp">더보기</a>
          </div>
        </div>
      </div>

      <a class="carousel-control-prev" href="#heroCarousel" role="button" data-bs-slide="prev">
        <span class="carousel-control-prev-icon bx bx-chevron-left" aria-hidden="true"></span>
      </a>

      <a class="carousel-control-next" href="#heroCarousel" role="button" data-bs-slide="next">
        <span class="carousel-control-next-icon bx bx-chevron-right" aria-hidden="true"></span>
      </a>

    </div>
  </section><!-- End Hero -->

  <main id="main">

    <!-- ======= 주변 추천 관광지 Section ======= -->
    <section class="features">
      <div class="container">

        <div class="section-title" data-aos="fade-up">
          <h2>주변 추천 관광지</h2>
        </div>

        <div class="location-based">     
    </section><!-- End 주변 추천 관광지 Section -->

  </main><!-- End #main -->

  <script src="${pageContext.request.contextPath}/assets/js/index.js"></script>
<%@ include file="./components/footer.jsp" %>