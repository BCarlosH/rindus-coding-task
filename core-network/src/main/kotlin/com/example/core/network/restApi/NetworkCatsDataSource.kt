package com.example.core.network.restApi

import com.example.core.network.model.NetworkCat

interface NetworkCatsDataSource {
    suspend fun getCats(): List<NetworkCat>
}
