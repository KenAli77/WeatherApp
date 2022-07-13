package ken.projects.weatherapp.data.mappers

import ken.projects.weatherapp.data.remote.WeatherDTO
import ken.projects.weatherapp.data.remote.WeatherDataDTO
import ken.projects.weatherapp.domain.weather.DailyWeatherInfo
import ken.projects.weatherapp.domain.weather.WeatherData
import ken.projects.weatherapp.domain.weather.WeatherInfo
import ken.projects.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)


fun WeatherDataDTO.toWeatherDataMap(): Map<Int, List<WeatherData>> {

    // mapping weather data object to indexed data
    return time.mapIndexed { index, time ->

        val temperature = temperatureData[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeedData[index]
        val apparentTemperature = apparentTemperatureData[index]
        val humidity = humidityData[index]

        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                apparentTemperature = apparentTemperature,
                windSpeed = windSpeed,
                weatherType = WeatherType.fromWMO(weatherCode),
                humidity = humidity
            )
        )

    }.groupBy {
        // json response lists weather data per hour for the next 7 days
        // by dividing by 24 we get data grouped by day
        it.index / 24
    }.mapValues { map ->
        map.value.map { it.data }
    }


}

fun WeatherDTO.toWeatherInfo(): WeatherInfo {


    val weatherDataMap = weatherData.toWeatherDataMap()
    val currentDate = LocalDateTime.now()

    // first element of the map corresponds to the current day
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (currentDate.minute < 30) currentDate.hour else currentDate.hour + 1
        it.time.hour == hour
    }
    // second element corresponds to the next day
    val nextDayWeatherData = weatherDataMap[1]?.find {
        val hour = if (currentDate.minute < 30) currentDate.hour else currentDate.hour + 1
        it.time.hour == hour
    }

    // daily weather data

    val dailWeatherDataList = ArrayList<DailyWeatherInfo>()

    // creating daily forecast data objects
    weatherDataMap.forEach { data ->
        val maxTemp = data.value.maxOf { it.temperatureCelsius }
        val minTemp = data.value.minOf { it.temperatureCelsius }
        val date = data.value[0].time
        val weatherType = data.value[11].weatherType

        dailWeatherDataList.add(
            DailyWeatherInfo(
                time = date,
                maxTemp = maxTemp,
                minTemp = minTemp,
                weatherType = weatherType
            )
        )

    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData,
        nextDayWeatherData = nextDayWeatherData,
        dailyWeatherInfo = dailWeatherDataList
    )
}
