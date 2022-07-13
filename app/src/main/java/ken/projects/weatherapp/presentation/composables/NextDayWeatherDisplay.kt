package ken.projects.weatherapp.presentation.composables

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import ken.projects.weatherapp.R
import ken.projects.weatherapp.presentation.ui.theme.darkWhite
import ken.projects.weatherapp.presentation.ui.theme.deepBlue
import ken.projects.weatherapp.presentation.viewmodel.WeatherState
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun NextDayWeatherDisplay(
    state: WeatherState,
    context: Context
) {

    state.weatherInfo?.nextDayWeatherData?.let { data ->

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, end = 0.dp, start = 0.dp)
                .background(darkWhite),

            ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {

                Spacer(modifier = Modifier.height(5.dp))

                Divider(
                    Modifier
                        .width(50.dp)
                        .height(3.dp), color = deepBlue
                )
                Row(
                    modifier = Modifier.padding(top = 10.dp, end = 0.dp, start = 0.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(top = 50.dp)
                    ) {
                        Text(
                            text = context.getString(R.string.tomorrow),
                            color = deepBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = data.time.format(
                                DateTimeFormatter.ofPattern(
                                    "dd MMM, EEE",
                                    Locale.ENGLISH
                                )
                            ),
                            fontSize = 18.sp,
                            color = Color.LightGray,
                            textAlign = TextAlign.Start
                        )

                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        val composition by rememberLottieComposition(
                            LottieCompositionSpec.RawRes(
                                data.weatherType.iconRes
                            )
                        )
                        LottieAnimation(
                            composition = composition,
                            modifier = Modifier
                                .height(170.dp)
                                .width(170.dp),
                            isPlaying = true
                        )

                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(top = 50.dp)
                    ) {
                        Text(
                            text = "${data.temperatureCelsius}°",
                            color = deepBlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            textAlign = TextAlign.End
                        )

                        Text(
                            text = context.getString(data.weatherType.weatherDesc),
                            color = Color.LightGray,
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            textAlign = TextAlign.End
                        )

                    }
                }

                WeatherDataDisplay(
                    textColor = deepBlue,
                    humidity = data.humidity.toInt(),
                    windSpeed = data.windSpeed.toInt(),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                    context = context
                )

            }

        }


    }


}