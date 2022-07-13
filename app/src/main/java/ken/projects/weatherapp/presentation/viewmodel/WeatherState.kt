package ken.projects.weatherapp.presentation.viewmodel

import ken.projects.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading:Boolean = false,
    val cityName:String? = "",
    val error:String? = null
)