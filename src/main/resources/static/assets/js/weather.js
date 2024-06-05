const icon = document.querySelector(".weather .icon");
const temp = document.querySelector(".weather .temp");
let lat = ''
let lon = ''

function getGeoLocation() {
  return new Promise((resolve, reject) => {
    navigator.geolocation.getCurrentPosition(resolve, reject);
  });
}

function connectGeo(position) {
  lat = position.coords.latitude;
  lon = position.coords.longitude;
  const URL = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${weatherApikey}&units=metric`;
  fetch(URL).then((response) => response.json()).then((data) => {
    const weatherIconAdrs = `http://openweathermap.org/img/wn/${data.weather[0].icon}.png`

    icon.setAttribute('src', weatherIconAdrs)
    temp.append(`${data.main.temp}℃`);
  });
}

function errorGeo() {
  console.error("위치 연결이 안됨");
}

// getGeoLocation 함수 호출 후 then을 사용하여 connectGeo 및 console.log 호출
getGeoLocation().then(position => {
  return connectGeo(position);
}).then(() => {
  console.log(lat, lon);
  $.get('/test', {'lat':lat, 'lon':lon})
}).catch(errorGeo);

// navigator.geolocation.getCurrentPosition(connectGeo, errorGeo);

// console.log(lat, lon)