package ken.projects.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDTO(

    @field:Json(name = "hourly")
    val weatherData: WeatherDataDTO
)