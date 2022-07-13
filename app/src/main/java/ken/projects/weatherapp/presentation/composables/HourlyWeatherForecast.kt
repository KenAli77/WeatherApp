package ken.projects.weatherapp.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ken.projects.weatherapp.presentation.ui.theme.darkWhite
import ken.projects.weatherapp.presentation.viewmodel.WeatherState

@Composable
fun HourlyWeatherForecast(

    state: WeatherState
) {

    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 0.5.dp, end = 0.dp)
                ,
            shape = RoundedCornerShape(40.dp,0.dp,0.dp,40.dp),
            backgroundColor = darkWhite,
            elevation = 2.dp,

        ) {

            LazyRow(content = {
                items(data) { weatherData ->
                    HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                    Divider(
                        color = Color.LightGray.copy(alpha = 0.2f),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp),
                    )
                }
            }, modifier = Modifier.height(75.dp))

        }
    }
}