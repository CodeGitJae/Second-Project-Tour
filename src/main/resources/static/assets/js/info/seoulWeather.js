const key = '66c8c034ba499f2284b9f07e02e51504';
const URL = `https://api.openweathermap.org/data/2.5/forecast?q=Seoul&appid=${key}&units=metric&lang=kr`;

// 시간대별 예보 및 5일 예보 가져오기
fetch(URL)
.then(response => response.json())
.then(data => {
    const hourlyForecastElement = document.getElementById('hourly-forecast');
    const dailyForecastElement = document.getElementById('daily-forecast');
    const hourlyData = data.list.slice(0, 8); // 오늘 시간대별 예보 (3시간 간격, 총 8개)
    const dailyData = data.list.filter((_, index) => index % 8 === 0); // 5일 예보 (하루에 한 번씩)
    
    hourlyData.forEach(forecast => {
      const time = new Date(forecast.dt * 1000).toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'});
      const temp = forecast.main.temp;
      const icon = forecast.weather[0].icon;
      const description = forecast.weather[0].description;
      hourlyForecastElement.innerHTML += `
          <div class="col text-center">
              <h5>${time}</h5>
              <img src="https://openweathermap.org/img/wn/${icon}@2x.png" alt="${description}" class="weather-icon">
              <p>${temp}°C</p>
              <p>${description}</p>
          </div>
      `;
    });

    dailyData.forEach(forecast => {
        const date = new Date(forecast.dt * 1000).toLocaleDateString('ko-KR');
        const temp = forecast.main.temp;
        const icon = forecast.weather[0].icon;
        const description = forecast.weather[0].description;
        dailyForecastElement.innerHTML += `
            <div class="col text-center">
                <h5>${date}</h5>
                <img src="https://openweathermap.org/img/wn/${icon}@2x.png" alt="${description}" class="weather-icon">
                <p>${temp}°C</p>
                <p>${description}</p>
            </div>
        `;
    });
});