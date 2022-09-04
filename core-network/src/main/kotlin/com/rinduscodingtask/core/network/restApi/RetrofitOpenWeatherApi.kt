package com.rinduscodingtask.core.network.restApi

import com.rinduscodingtask.core.network.model.NetworkCurrentWeather
import com.rinduscodingtask.core.network.utils.validateResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

private const val API_ID = "c3b5c1f6e624c769c0a1d863fdfca845"
private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val CITY = "Düsseldorf"
private const val COUNTRY_CODE = "DE"
private const val UNITS_OF_MEASUREMENT = "metric"

private interface RetrofitOpenWeatherApi {


    @GET("weather?q=$CITY,$COUNTRY_CODE&units=$UNITS_OF_MEASUREMENT&APPID=$API_ID")
    suspend fun getCurrentWeather(): Response<NetworkCurrentWeather>
}

@Singleton
class NetworkWeatherDataSourceImpl @Inject constructor(
    okHttpClient: OkHttpClient,
) : NetworkWeatherDataSource {

    private val networkApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
        .create(RetrofitOpenWeatherApi::class.java)

    override suspend fun getCurrentWeather(): NetworkCurrentWeather {
        return networkApi.getCurrentWeather().validateResponse()
    }
}
