package ken.projects.weatherapp.presentation.composables

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import ken.projects.weatherapp.R
import ken.projects.weatherapp.presentation.ui.theme.lightGray
import ken.projects.weatherapp.presentation.viewmodel.WeatherState
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun CurrentWeatherDisplay(
    state: WeatherState,
    modifier: Modifier = Modifier,
    context: Context
) {
    state.weatherInfo?.currentWeatherData?.let { data ->

        Box()
         {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 20.dp, 15.dp, 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = data.time.format(
                        DateTimeFormatter.ofPattern(
                            "d MMM, EEEE",
                            Locale.ENGLISH
                        )
                    ),
                    modifier = Modifier.align(Alignment.Start),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.align(Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = state.cityName.toString(),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_outward),
                        contentDescription = null,
                        tint = lightGray.copy(0.6f),
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                    )
                }

                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(data.weatherType.iconRes))
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    iterations = Int.MAX_VALUE
                )
                Text(
                    text = context.getString(data.weatherType.weatherDesc),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 10.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal
                )
                Row(
                    modifier = modifier.align(Alignment.Start),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Text(
                        text = "${data.temperatureCelsius}°C",
                        fontSize = 60.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,

                        )

                    Spacer(modifier = Modifier.width(40.dp))

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = context.getString(R.string.feels_like),
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Light,

                            )
                        Text(
                            text = "${data.apparentTemperature}°C",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,

                            )

                    }

                }

                Spacer(modifier = Modifier.height(15.dp))

                WeatherDataDisplay(
                    modifier = modifier.fillMaxWidth(),
                    humidity = data.humidity.toInt(),
                    windSpeed = data.windSpeed.toInt(),
                    context = context
                )


            }


        }


    }


}