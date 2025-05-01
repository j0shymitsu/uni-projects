async function getWeather(location) {
    const key = 'c12763dc822705b393c16b6bc00712ae';
    const url = `https://api.weatherstack.com/current?access_key=${key}&query=${location}`;
    const response = await fetch(url);
    const data = await response.json();
    return data;
}

getWeather('Chester')
    .then(data => {
        const temp = data.current.temperature;
        const desc = data.current.weather_descriptions[0];
        document.getElementById('weather').innerHTML = `Current weather at Chester University: ${temp}c - ${desc}`;
    })
    .catch(err => {
        //fallback
        document.getElementById('weather').textContent = 
        'Weather information is currently unavailable!'
    })