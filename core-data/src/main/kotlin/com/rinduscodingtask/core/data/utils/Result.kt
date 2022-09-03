package com.rinduscodingtask.core.data.utils

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val errorMessage: String) : Result<Nothing>
}
