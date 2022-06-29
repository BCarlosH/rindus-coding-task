package com.example.core.data.utils

sealed interface Result<out T> {
    object Loading : Result<Nothing>
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable? = null) : Result<Nothing>
}
