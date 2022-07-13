package ken.projects.weatherapp.presentation.composables

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import ken.projects.weatherapp.domain.weather.DailyWeatherInfo
import ken.projects.weatherapp.presentation.ui.theme.deepBlue
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun WeekWeatherItem(data: DailyWeatherInfo,context: Context) {

    val formattedDate = data.time.format(
        DateTimeFormatter.ofPattern(
            "d MMM, ",
            Locale.ENGLISH
        )
    )

    val formattedDay = data.time.format(
        DateTimeFormatter.ofPattern(
            "EEE",
            Locale.ENGLISH
        )
    )


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = formattedDate,
                fontSize = 20.sp,
                color = deepBlue,
            )
            Text(
                text = formattedDay,
                fontSize = 16.sp,
                color = Color.LightGray
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1.2f)
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(data.weatherType.iconRes))
            LottieAnimation(
                composition = composition,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp),
                isPlaying = true
            )
            Text(
                text = data.maxTemp.toString(),
                color = deepBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = data.minTemp.toString() + "°",
                color = Color.LightGray,
                fontSize = 15.sp,
            )
        }

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = context.getString(data.weatherType.weatherDesc),
                color = deepBlue,


                )
        }


    }

}