package com.example.core.network.utils

import com.example.core.network.exceptions.UnknownNetworkException
import retrofit2.Response

fun <T> Response<T>.validateResponse(): T {
    val data = this.body()
    return if (this.isSuccessful && data != null) {
        data
    } else {
        throw UnknownNetworkException("${this.code()} ${this.message()}")
    }
}
