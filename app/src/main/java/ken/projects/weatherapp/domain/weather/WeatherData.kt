package ken.projects.weatherapp.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius:Double,
    val apparentTemperature:Double,
    val windSpeed:Double,
    val humidity:Double,
    val weatherType: WeatherType,

)