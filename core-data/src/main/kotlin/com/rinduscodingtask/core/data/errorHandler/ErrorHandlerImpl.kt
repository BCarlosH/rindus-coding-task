package com.rinduscodingtask.core.data.errorHandler

import com.rinduscodingtask.core.data.R
import com.rinduscodingtask.core.data.utils.ResourceProvider
import com.rinduscodingtask.core.network.exceptions.NoConnectivityException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor(
    private val resourceProvider: ResourceProvider,
) : ErrorHandler {

    override fun parseError(exception: Throwable?): String {
        return when (exception) {
            is NoConnectivityException -> resourceProvider.getString(R.string.connectivity_error)
            else -> resourceProvider.getString(R.string.generic_error)
        }
    }
}
