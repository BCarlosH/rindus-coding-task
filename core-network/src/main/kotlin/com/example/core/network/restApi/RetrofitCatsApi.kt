package com.example.core.network.restApi

import com.example.core.network.model.NetworkCat
import com.example.core.network.utils.validateResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = "https://run.mocky.io/v3/"

interface RetrofitCatsApi {
    @GET("03256b12-927c-4b06-ba0a-b527cc83943a")
    suspend fun getCats(): Response<List<NetworkCat>>
}

@Singleton
class NetworkCatsDataSourceImpl @Inject constructor(
    okHttpClient: OkHttpClient,
) : NetworkCatsDataSource {

    private val networkApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
        .create(RetrofitCatsApi::class.java)

    override suspend fun getCats(): List<NetworkCat> {
        return networkApi.getCats().validateResponse()
    }
}
