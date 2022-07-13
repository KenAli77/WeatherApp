package ken.projects.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("/v1/forecast?hourly=temperature_2m,relativehumidity_2m,apparent_temperature,weathercode,windspeed_10m")
    suspend fun fetchWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherDTO
}