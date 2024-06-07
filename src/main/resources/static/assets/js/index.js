document.addEventListener("DOMContentLoaded", function() {
  
  // getGeoLocation 함수 호출 후 then을 사용하여 connectGeo 및 console.log 호출
  getGeoLocation().then(position => {
    return connectGeo(position);
  }).then(() => {
    // 서울이 아닌 위경도 테스트용 코드(부산위경도임)
    // lat = 35.1796
    // lon = 129.0756

    console.log(lat, lon);

    // 서울의 위도와 경도 범위
    const minLat = 37.4;
    const maxLat = 37.7;
    const minLon = 126.8;
    const maxLon = 127.2;

    // 주어진 위도와 경도가 서울의 범위 내에 있는지 확인
    if (lat < minLat || lat > maxLat || lon < minLon || lon > maxLon) {
      // 서울 범위를 벗어나면 서울 중심 위경도로 변경
      lat = 37.5665
      lon = 126.9780
    } 

    getLocationBasedList(lat, lon);
    
  }).catch(() => {
    // 위치 정보 허용을 안하면 서울 중심으로 위경도 설정
    lat = 37.5665
    lon = 126.9780

    getLocationBasedList(lat, lon);
  });
});

function getLocationBasedList(lat, lon) {
  const locContainer = document.querySelector('.location-based');
  
  $.ajax({
    url: "/test",
    data: {'lat':lat, 'lon':lon},
    type: "GET",
    success: function(response) {
        console.log(response);

        response.forEach((data, i) => {
          const dist = (Math.floor(Number(data.dist))/1000).toFixed(1);
          let content = `
            <div class="row" data-aos="fade-up">
            `;
          
          if(i % 2 == 1) {
            content += `<div class="col-md-5">`;
          } else {
            content += `<div class="col-md-5 order-1 order-md-2">`;
          }
          content += `
            <img src="${data.firstimage}" class="img-fluid" alt="">
              </div>
              <div class="col-md-7 pt-4">
                <h3>${data.title}</h3>
                <p>거리 : 약 ${dist}km</p>
                <p class="fst-italic">
                  주소 : ${data.addr1} ${data.addr2}
                </p>
              </div>
            </div>
            `;
          locContainer.innerHTML += content;
        });
    },
    error: function(error) {
        console.error("Error:", error);
    }
  });
}