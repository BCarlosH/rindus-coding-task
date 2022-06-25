package com.example.core.network.restApi

import retrofit2.http.GET

interface CatsService {
    @GET("03256b12-927c-4b06-ba0a-b527cc83943a")
    suspend fun getCats()
}
