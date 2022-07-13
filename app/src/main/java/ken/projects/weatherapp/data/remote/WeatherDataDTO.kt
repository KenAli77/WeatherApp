package ken.projects.weatherapp.data.remote

import com.squareup.moshi.Json

// Data Transfer Object
// This class contains variables to translate the Json response from API
data class WeatherDataDTO(

    val time : List<String>,

    @field:Json(name = "temperature_2m")
    val temperatureData: List<Double>,

    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,

    @field:Json(name = "windspeed_10m")
    val windSpeedData: List<Double>,

    @field:Json(name = "apparent_temperature")
    val apparentTemperatureData: List<Double>,

    @field:Json(name = "relativehumidity_2m")
    val humidityData: List<Double>
)
