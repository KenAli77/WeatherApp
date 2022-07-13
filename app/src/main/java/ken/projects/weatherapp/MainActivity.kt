package ken.projects.weatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import ken.projects.weatherapp.presentation.composables.CurrentWeatherDisplay
import ken.projects.weatherapp.presentation.composables.HourlyWeatherForecast
import ken.projects.weatherapp.presentation.composables.WeekWeatherForecast
import ken.projects.weatherapp.presentation.ui.theme.WeatherAppTheme
import ken.projects.weatherapp.presentation.ui.theme.darkWhite
import ken.projects.weatherapp.presentation.ui.theme.lightBlue
import ken.projects.weatherapp.presentation.ui.theme.orange
import ken.projects.weatherapp.presentation.viewmodel.WeatherViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        setContent {

            WeatherAppTheme {

                val bottomSheetState =
                    rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
                val scaffoldState =
                    rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
                if (viewModel.state.isLoading) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    listOf(lightBlue, darkWhite),
                                    endY = 2000f,
                                    startY = 250f
                                )
                            ),
                    ) {
                        CircularProgressIndicator(
                            color = orange.copy(0.6f),
                            strokeWidth = 5.dp,
                        )
                    }

                }
                viewModel.state.error?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                }
                if (!viewModel.state.isLoading && viewModel.state.error.isNullOrEmpty())
                    BottomSheetScaffold(
                        sheetContent = {
                            WeekWeatherForecast(state = viewModel.state,this@MainActivity)
                        },
                        sheetPeekHeight = 240.dp,
                        sheetShape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp),
                        sheetBackgroundColor = darkWhite,
                        scaffoldState = scaffoldState
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            listOf(lightBlue, darkWhite, darkWhite),
                                            endY = 2000f,
                                            startY = 250f
                                        )
                                    )
                            ) {
                                CurrentWeatherDisplay(state = viewModel.state, context = this@MainActivity)
                                Spacer(modifier = Modifier.height(16.dp))
                                HourlyWeatherForecast(state = viewModel.state)


                            }


                        }

                    }
            }

        }
    }
}
