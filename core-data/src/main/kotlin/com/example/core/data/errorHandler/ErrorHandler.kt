package com.example.core.data.errorHandler

interface ErrorHandler {
    fun parseError(exception: Throwable?): String
}
