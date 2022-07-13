package ken.projects.weatherapp.presentation.composables

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ken.projects.weatherapp.R
import ken.projects.weatherapp.presentation.ui.theme.darkWhite
import ken.projects.weatherapp.presentation.ui.theme.deepBlue
import ken.projects.weatherapp.presentation.viewmodel.WeatherState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeekWeatherForecast(
    state: WeatherState,
    context: Context
) {

    state.weatherInfo?.dailyWeatherInfo?.let { data ->

        Column() {

            NextDayWeatherDisplay(state = state, context = context)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(darkWhite)
                    .padding(start = 5.dp, end = 5.dp, bottom = 40.dp),

                ) {

                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    Text(
                        text = context.getString(R.string.seven_days_forecast),
                        color = deepBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    LazyColumn(
                        content = {

                            items(data) { weatherData ->
                                WeekWeatherItem(
                                    data = weatherData,
                                    context = context
                                )

                                Divider(
                                    color = Color.LightGray.copy(alpha = 0.2f),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp),
                                )

                            }


                        },

                        )
                }


            }
        }


    }


}