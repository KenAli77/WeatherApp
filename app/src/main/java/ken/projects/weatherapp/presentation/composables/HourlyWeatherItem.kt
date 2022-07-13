package ken.projects.weatherapp.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import ken.projects.weatherapp.domain.weather.WeatherData
import ken.projects.weatherapp.presentation.ui.theme.deepBlue
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
) {

    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")

        )
    }
    Row(
        modifier = modifier.height(73.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(weatherData.weatherType.iconRes))
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .height(70.dp)
                .width(70.dp)
            ,
            isPlaying = true
        )

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "${weatherData.temperatureCelsius}°",
                color = deepBlue,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = formattedTime,
                color = Color.LightGray
            )

        }




    }

}