package com.rinduscodingtask.core.data

import com.rinduscodingtask.core.data.errorHandler.ErrorHandler
import com.rinduscodingtask.core.data.repository.WeatherRepository
import com.rinduscodingtask.core.data.repository.WeatherRepositoryImpl
import com.rinduscodingtask.core.data.utils.*
import com.rinduscodingtask.core.model.CurrentWeather
import com.rinduscodingtask.core.model.Forecast
import com.rinduscodingtask.core.network.model.*
import com.rinduscodingtask.core.network.restApi.NetworkWeatherDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryTest {

    private val networkWeatherDataSource = mockk<NetworkWeatherDataSource>()
    private val errorHandler = mockk<ErrorHandler>()
    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setup() {
        weatherRepository = WeatherRepositoryImpl(
            dispatcher = UnconfinedTestDispatcher(),
            networkWeatherDataSource = networkWeatherDataSource,
            errorHandler = errorHandler
        )
    }

    @Test
    fun getCurrentWeather_whenSuccess_mapsToDomainModelTest() = runTest {
        coEvery { networkWeatherDataSource.getCurrentWeather() } returns networkCurrentWeather
        val currentWeather = weatherRepository.getCurrentWeather()

        assertEquals(
            successCurrentWeather,
            currentWeather.first()
        )
    }

    @Test
    fun getCurrentWeather_whenExceptionIsThrown_returnsErrorTest() = runTest {
        val exception = Exception()
        coEvery { networkWeatherDataSource.getCurrentWeather() }.throws(exception)
        coEvery { errorHandler.parseError(exception) } returns errorResult.errorMessage
        val currentWeather = weatherRepository.getCurrentWeather()

        assertEquals(
            errorResult,
            currentWeather.first()
        )
    }

    @Test
    fun getFiveDaysForecast_whenSuccess_mapsToDomainModelTest() = runTest {
        coEvery { networkWeatherDataSource.getFiveDaysForecast() } returns networkFiveDaysForecast
        val fiveDaysForecast = weatherRepository.getFiveDaysForecast()

        assertEquals(
            successForecastList,
            fiveDaysForecast.first()
        )
    }

    @Test
    fun getFiveDaysForecast_whenExceptionIsThrown_returnsErrorTest() = runTest {
        val exception = Exception()
        coEvery { networkWeatherDataSource.getFiveDaysForecast() }.throws(exception)
        coEvery { errorHandler.parseError(exception) } returns errorResult.errorMessage
        val fiveDaysForecast = weatherRepository.getFiveDaysForecast()

        assertEquals(
            errorResult,
            fiveDaysForecast.first()
        )
    }
}

private val errorResult = Result.Error("error message")

private val successCurrentWeather = Result.Success(
    CurrentWeather(
        imageUrl = provideImageUrl("50d"),
        currentTemperature = 16.07,
        humidity = 92,
        pressure = 1011,
        windSpeed = 5.14
    )
)

private val networkCurrentWeather = NetworkCurrentWeather(
    base = "stations",
    clouds = Clouds(all = 75),
    cod = 200,
    coord = Coord(lat = 51.2217, lon = 6.7762),
    dt = 1662803223,
    id = 2934246,
    main = Main(
        feelsLike = 16.13,
        grndLevel = 0,
        humidity = 92,
        pressure = 1011,
        seaLevel = 0,
        temp = 16.07,
        tempKf = 0.0,
        tempMax = 18.34,
        tempMin = 14.56
    ),
    name = "Düsseldorf",
    sys = Sys(
        country = "DE",
        id = 2019396,
        sunrise = 1662785996,
        sunset = 1662832819,
        type = 2,
        pod = ""
    ),
    timezone = 7200,
    visibility = 2900,
    weather = listOf(Weather(description = "mist", icon = "50d", id = 701, main = "Mist")),
    wind = Wind(deg = 180, gust = 0.0, speed = 5.14)
)

private val successForecastList = Result.Success(
    listOf(
        Forecast(
            imageUrl = provideImageUrl("10d"),
            day = dateTimeFormatter(time = 1662822000, format = DAY_FORMAT),
            hour = dateTimeFormatter(time = 1662822000, format = HOUR_FORMAT),
            maxTemperature = 18.46,
            minTemperature = 16.29
        ),
        Forecast(
            imageUrl = provideImageUrl("10d"),
            day = dateTimeFormatter(time = 1662811200, format = DAY_FORMAT),
            hour = dateTimeFormatter(time = 1662811200, format = HOUR_FORMAT),
            maxTemperature = 18.46,
            minTemperature = 16.29
        )
    )
)

private val networkFiveDaysForecast = NetworkFiveDaysForecast(
    city = City(
        coord = Coord(lat = 51.2217, lon = 6.7762),
        country = "DE",
        id = 2934246,
        name = "Düsseldorf",
        population = 15000,
        sunrise = 1662785996,
        sunset = 1662832819,
        timezone = 7200
    ),
    cnt = 40,
    cod = "200",
    forecastList = listOf(
        NetworkForecast(
            clouds = Clouds(all = 100),
            dataTime = 1662822000,
            dataTimeIso = "2022-09-10 15:00:00",
            main = Main(
                feelsLike = 16.35,
                grndLevel = 1006,
                humidity = 91,
                pressure = 1011,
                seaLevel = 1011,
                temp = 16.29,
                tempKf = -2.17,
                tempMax = 18.46,
                tempMin = 16.29
            ),
            pop = 0.83,
            rain = Rain(volumeLastThreeHours = 1.47),
            sys = Sys(country = "null", id = 0, sunrise = 0, sunset = 0, type = 0, pod = ""),
            visibility = 10000,
            weather = listOf(
                Weather(
                    description = "light rain",
                    icon = "10d",
                    id = 500,
                    main = "Rain"
                )
            ),
            wind = Wind(deg = 240, gust = 9.01, speed = 4.86)
        ),
        NetworkForecast(
            clouds = Clouds(all = 100),
            dataTime = 1662811200,
            dataTimeIso = "2022 - 09 - 1012:00:00",
            main = Main(
                feelsLike = 16.35,
                grndLevel = 1006,
                humidity = 91,
                pressure = 1011,
                seaLevel = 1011,
                temp = 16.29,
                tempKf = -2.17,
                tempMax = 18.46,
                tempMin = 16.29
            ),
            pop = 0.83,
            rain = Rain(volumeLastThreeHours = 1.47),
            sys = Sys(country = "null", id = 0, sunrise = 0, sunset = 0, type = 0, pod = ""),
            visibility = 10000,
            weather = listOf(
                Weather(
                    description = "light rain",
                    icon = "10d",
                    id = 500,
                    main = "Rain"
                )
            ),
            wind = Wind(deg = 240, gust = 9.01, speed = 4.86)
        )
    ), message = 0
)
