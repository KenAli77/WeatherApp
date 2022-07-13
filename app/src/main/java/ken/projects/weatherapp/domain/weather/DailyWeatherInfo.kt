package ken.projects.weatherapp.domain.weather

import java.time.LocalDateTime

data class DailyWeatherInfo(

    val time: LocalDateTime,
    val maxTemp: Double,
    val minTemp: Double,
    val weatherType: WeatherType
)
