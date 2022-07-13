package ken.projects.weatherapp.presentation.viewmodel

import android.app.Application
import android.location.Geocoder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ken.projects.weatherapp.R
import ken.projects.weatherapp.domain.location.LocationTracker
import ken.projects.weatherapp.domain.repository.WeatherRepository
import ken.projects.weatherapp.domain.util.Resource
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker,
    private val app:Application
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {

        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->

                val result = repository.fetchWeatherData(location.latitude, location.longitude)
                val geocoder = Geocoder(app, Locale.ENGLISH)
                val address = geocoder.getFromLocation(location.latitude, location.longitude,1)

                val cityName = address[0]




                when (result) {

                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null,
                            cityName = cityName.locality
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message,
                            cityName = cityName.locality
                        )
                    }
                }

            }?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = app.getString(R.string.error_location)
                )

            }
        }

    }
}