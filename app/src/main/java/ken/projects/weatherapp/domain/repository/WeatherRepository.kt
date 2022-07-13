package ken.projects.weatherapp.domain.repository

import ken.projects.weatherapp.domain.util.Resource
import ken.projects.weatherapp.domain.weather.WeatherInfo

//weather repository abstraction
interface WeatherRepository {

    suspend fun fetchWeatherData(lat:Double,long:Double): Resource<WeatherInfo>
}