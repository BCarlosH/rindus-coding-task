package com.rinduscodingtask.core.data.errorHandler

interface ErrorHandler {
    fun parseError(exception: Throwable?): String
}
