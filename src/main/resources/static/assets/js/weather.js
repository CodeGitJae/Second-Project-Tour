let icon
let temp
let lat
let lon
let getGeoLocation
let connectGeo
let error

document.addEventListener("DOMContentLoaded", function() {
   icon = document.querySelector(".weather .icon");
   temp = document.querySelector(".weather .temp");
   lat = ''
   lon = ''
  
  getGeoLocation = () => {
    return new Promise((resolve, reject) => {
      navigator.geolocation.getCurrentPosition(resolve, reject);
    });
  }
  
  connectGeo = (position) => {
    lat = position.coords.latitude;
    lon = position.coords.longitude;
    
    const URL = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${weatherApikey}&units=metric`;
    fetch(URL).then((response) => response.json()).then((data) => {
      const weatherIconAdrs = `http://openweathermap.org/img/wn/${data.weather[0].icon}.png`
  
      icon.setAttribute('src', weatherIconAdrs)
      temp.append(`${data.main.temp}℃`);
    });
  }
  
  errorGeo = () => {
    console.error("위치 연결이 안됨");
  }

  const uri = window.location.pathname;

  if(uri !== '/') {
    getGeoLocation().then(position => {
      return connectGeo(position);
    })
  }
  
});


// navigator.geolocation.getCurrentPosition(connectGeo, errorGeo);

// console.log(lat, lon)