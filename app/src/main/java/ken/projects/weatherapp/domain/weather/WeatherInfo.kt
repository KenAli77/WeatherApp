package ken.projects.weatherapp.domain.weather

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?,
    val nextDayWeatherData:WeatherData?,
    val dailyWeatherInfo: List<DailyWeatherInfo>?
)
