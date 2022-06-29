package com.example.core.network.utils

import com.example.core.network.exceptions.RetrofitNetworkException
import retrofit2.Response

internal fun <T> Response<T>.validateResponse(): T {
    val data = this.body()
    return if (this.isSuccessful && data != null) {
        data
    } else {
        throw RetrofitNetworkException("${this.code()} ${this.message()}")
    }
}
