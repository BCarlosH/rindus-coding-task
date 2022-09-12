package com.rinduscodingtask.feature.weather

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.rinduscodingtask.core.data.utils.DAY_FORMAT
import com.rinduscodingtask.core.data.utils.HOUR_FORMAT
import com.rinduscodingtask.core.data.utils.dateTimeFormatter
import com.rinduscodingtask.core.data.utils.provideImageUrl
import com.rinduscodingtask.core.model.CurrentWeather
import com.rinduscodingtask.core.model.Forecast
import org.junit.Rule
import org.junit.Test

class WeatherScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val resources = InstrumentationRegistry.getInstrumentation().targetContext.resources

    @Test
    fun loading_whenIsLoading_showLoading() {
        composeTestRule.setContent {
            WeatherScreen(
                weatherUiState = WeatherUiState(
                    CurrentWeatherUiState.Loading,
                    FiveDaysForecastUiState.Loading
                )
            )
        }

        with(composeTestRule) {
            onNodeWithText(resources.getString(R.string.header_current_weather))
                .assertExists()

            onNodeWithTag(CurrentWeatherLoadingTestTag)
                .assertExists()
            onNodeWithTag(FiveDaysForecastLoadingTestTag)
                .assertExists()
        }
    }

    @Test
    fun currentWeather_whenIsSuccess_showContent() {
        composeTestRule.setContent {
            WeatherScreen(
                weatherUiState = WeatherUiState(
                    CurrentWeatherUiState.Success(successCurrentWeather),
                    FiveDaysForecastUiState.Loading
                )
            )
        }

        with(composeTestRule) {
            onNodeWithText(resources.getString(R.string.header_current_weather))
                .assertExists()

            onNodeWithContentDescription(resources.getString(R.string.desc_weather_icon))
                .assertExists()

            onNodeWithText(
                resources.getString(
                    R.string.label_degrees,
                    successCurrentWeather.currentTemperature.toString().substringBefore(".")
                )
            ).assertExists()

            onNodeWithText(resources.getString(R.string.celsius))
                .assertExists()

            onNodeWithText(
                resources.getString(
                    R.string.label_humidity,
                    successCurrentWeather.humidity
                )
            ).assertExists()

            onNodeWithText(
                resources.getString(
                    R.string.label_pressure,
                    successCurrentWeather.pressure
                )
            ).assertExists()

            onNodeWithText(
                resources.getString(
                    R.string.label_wind_speed,
                    successCurrentWeather.windSpeed
                )
            ).assertExists()

            onNodeWithTag(FiveDaysForecastLoadingTestTag)
                .assertExists()
        }
    }

    @Test
    fun fiveDaysForecast_whenIsSuccess_showContent() {
        composeTestRule.setContent {
            WeatherScreen(
                weatherUiState = WeatherUiState(
                    CurrentWeatherUiState.Loading,
                    FiveDaysForecastUiState.Success(successForecastList)
                )
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("testlog")

        with(composeTestRule) {
            onNodeWithText(resources.getString(R.string.header_current_weather))
                .assertExists()

            onNodeWithTag(CurrentWeatherLoadingTestTag)
                .assertExists()

            onNodeWithText(resources.getString(R.string.header_forecast))
                .assertExists()

            onNodeWithText(successForecastList[0].day)
                .assertExists()

            onNodeWithText(successForecastList[1].hour).onSiblings()
                .filterToOne(hasText(""))
                .assertExists()

            onNodeWithText(successForecastList[0].hour)
                .assertExists()

            onNodeWithText(successForecastList[0].day).onSiblings()
                .filterToOne(hasContentDescription(resources.getString(R.string.desc_weather_icon)))
                .assertExists()

            onNodeWithText(
                resources.getString(
                    R.string.label_degrees,
                    successForecastList[0].maxTemperature.toString().substringBefore(".")
                )
            ).assertExists()

            onNodeWithText(
                resources.getString(
                    R.string.label_degrees,
                    successForecastList[0].minTemperature.toString().substringBefore(".")
                )
            ).assertExists()
        }
    }

    @Test
    fun error_whenIsError_showErrorMessage() {
        val currentWeatherError = "current weather error"
        val fiveDaysForecastError = "five days forecast error"

        composeTestRule.setContent {
            WeatherScreen(
                weatherUiState = WeatherUiState(
                    CurrentWeatherUiState.Error(currentWeatherError),
                    FiveDaysForecastUiState.Error(fiveDaysForecastError)
                )
            )
        }

        with(composeTestRule) {
            onNodeWithText(resources.getString(R.string.header_current_weather))
                .assertExists()

            onNodeWithText(currentWeatherError)
                .assertExists()
            onNodeWithText(fiveDaysForecastError)
                .assertExists()
        }
    }
}

private val successCurrentWeather = CurrentWeather(
    imageUrl = provideImageUrl("50d"),
    currentTemperature = 16.07,
    humidity = 92,
    pressure = 1011,
    windSpeed = 5.14
)

private val successForecastList = listOf(
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
        maxTemperature = 19.46,
        minTemperature = 17.29
    )
)
