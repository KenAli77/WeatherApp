package ken.projects.weatherapp.presentation.composables

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ken.projects.weatherapp.R

// @Preview
@Composable
fun WeatherDataDisplay(
    modifier: Modifier = Modifier,
    humidity: Int = 0,
    windSpeed: Int = 0,
    textColor: Color = Color.White,
    context: Context

    ) {

    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = context.getString(R.string.humidity),
                fontSize = 20.sp,
                color = textColor,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = "$humidity%", fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = textColor
            )

        }

        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = context.getString(R.string.wind),
                fontSize = 20.sp,
                color = textColor,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.width(30.dp))

            Text(
                text = "$windSpeed kmh",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = textColor
            )

        }

    }

}