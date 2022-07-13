package ken.projects.weatherapp.domain.weather

import android.annotation.SuppressLint
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import ken.projects.weatherapp.R

sealed class WeatherType(
    @StringRes val weatherDesc: Int,
    @RawRes val iconRes: Int,

    ) {

        object ClearSky : WeatherType(
            weatherDesc = R.string.Clear_sky,
            iconRes = R.raw.ic_sunny
        )

        @SuppressLint("ResourceType")
        object MainlyClear : WeatherType(
            weatherDesc = R.string.Mainly_clear,
            iconRes = R.raw.ic_cloudy
        )

        @SuppressLint("ResourceType")
        object PartlyCloudy : WeatherType(
            weatherDesc = R.string.Partly_cloudy,
            iconRes = R.raw.ic_cloudy
        )

        object Overcast : WeatherType(
            weatherDesc = R.string.Overcast,
            iconRes = R.raw.ic_overcast
        )

        object Foggy : WeatherType(
            weatherDesc = R.string.Foggy,
            iconRes = R.raw.ic_frost
        )

        object DepositingRimeFog : WeatherType(
            weatherDesc = R.string.Depositing_rime_fog,
            iconRes = R.raw.ic_frost
        )

        object LightDrizzle : WeatherType(
            weatherDesc = R.string.Light_drizzle,
            iconRes = R.raw.ic_light_rain
        )

        object ModerateDrizzle : WeatherType(
            weatherDesc = R.string.Moderate_drizzle,
            iconRes = R.raw.ic_moderate_rain
        )

        object DenseDrizzle : WeatherType(
            weatherDesc = R.string.Dense_drizzle,
            iconRes = R.raw.ic_heavy_rain
        )

        object LightFreezingDrizzle : WeatherType(
            weatherDesc = R.string.Light_freezing_drizzle,
            iconRes = R.raw.ic_hail
        )

        object DenseFreezingDrizzle : WeatherType(
            weatherDesc = R.string.Dense_freezing_drizzle,
            iconRes = R.raw.ic_hail
        )

        object SlightRain : WeatherType(
            weatherDesc = R.string.Slight_rain,
            iconRes = R.raw.ic_light_rain
        )

        object ModerateRain : WeatherType(
            weatherDesc = R.string.Rainy,
            iconRes = R.raw.ic_moderate_rain
        )

        object HeavyRain : WeatherType(
            weatherDesc =R.string.Heavy_rain,
            iconRes = R.raw.ic_heavy_rain
        )

        object HeavyFreezingRain : WeatherType(
            weatherDesc = R.string.Heavy_freezing_rain,
            iconRes = R.raw.ic_sleet
        )

        object SlightSnowFall : WeatherType(
            weatherDesc = R.string.Slight_snow_fall,
            iconRes = R.raw.ic_light_snow
        )

        object ModerateSnowFall : WeatherType(
            weatherDesc = R.string.Moderate_snow_fall,
            iconRes = R.raw.ic_moderate_snow
        )

        object HeavySnowFall : WeatherType(
            weatherDesc = R.string.Heavy_snow_fall,
            iconRes = R.raw.ic_heavy_snow
        )

        object SnowGrains : WeatherType(
            weatherDesc = R.string.Snow_grains,
            iconRes = R.raw.ic_heavy_snow
        )

        object SlightRainShowers : WeatherType(
            weatherDesc = R.string.Slight_rain,
            iconRes = R.raw.ic_the_rain_turned_fine
        )

        object ModerateRainShowers : WeatherType(
            weatherDesc = R.string.Moderate_showers,
            iconRes = R.raw.ic_moderate_rain
        )

        object ViolentRainShowers : WeatherType(
            weatherDesc = R.string.Violent_showers,
            iconRes = R.raw.ic_thunderstorm
        )

        object SlightSnowShowers : WeatherType(
            weatherDesc = R.string.Light_snow_showers,
            iconRes = R.raw.ic_light_snow
        )

        object HeavySnowShowers : WeatherType(
            weatherDesc =R.string.Heavy_snow_showers,
            iconRes = R.raw.ic_heavy_snow
        )

        object ModerateThunderstorm : WeatherType(
            weatherDesc = R.string.Moderate_thunderstorm,
            iconRes = R.raw.ic_thunderstorm
        )

        object SlightHailThunderstorm : WeatherType(
            weatherDesc = R.string.Thunderstorm_with_hail,
            iconRes = R.raw.ic_thunderstorm
        )

        object HeavyHailThunderstorm : WeatherType(
            weatherDesc =R.string.Thunderstorm_with_heavy_hail,
            iconRes = R.raw.ic_thunderstorm
        )

        companion object {

            fun fromWMO(code: Int): WeatherType {
                return when (code) {
                    0 -> ClearSky
                    1 -> MainlyClear
                    2 -> PartlyCloudy
                    3 -> Overcast
                    45 -> Foggy
                    48 -> DepositingRimeFog
                    51 -> LightDrizzle
                    53 -> ModerateDrizzle
                    55 -> DenseDrizzle
                    56 -> LightFreezingDrizzle
                    57 -> DenseFreezingDrizzle
                    61 -> SlightRain
                    63 -> ModerateRain
                    65 -> HeavyRain
                    66 -> LightFreezingDrizzle
                    67 -> HeavyFreezingRain
                    71 -> SlightSnowFall
                    73 -> ModerateSnowFall
                    75 -> HeavySnowFall
                    77 -> SnowGrains
                    80 -> SlightRainShowers
                    81 -> ModerateRainShowers
                    82 -> ViolentRainShowers
                    85 -> SlightSnowShowers
                    86 -> HeavySnowShowers
                    95 -> ModerateThunderstorm
                    96 -> SlightHailThunderstorm
                    99 -> HeavyHailThunderstorm
                    else -> ClearSky
                }
            }
        }
    }

