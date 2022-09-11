package com.rinduscodingtask.core.data

import com.rinduscodingtask.core.data.errorHandler.ErrorHandler
import com.rinduscodingtask.core.data.errorHandler.ErrorHandlerImpl
import com.rinduscodingtask.core.data.utils.ResourceProvider
import com.rinduscodingtask.core.network.exceptions.NoConnectivityException
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ErrorHandlerTest {

    private val resourceProvider = mockk<ResourceProvider>()
    lateinit var errorHandler: ErrorHandler

    @Before
    fun setup() {
        errorHandler = ErrorHandlerImpl(
            resourceProvider = resourceProvider
        )
    }

    @Test
    fun parseError_whenNoConnectivityException_matchesMessage() {
        val fakeErrorMessage = "Limited or no connectivity"
        val stringId = R.string.connectivity_error
        every { resourceProvider.getString(stringId) } returns fakeErrorMessage

        assertEquals(
            fakeErrorMessage,
            errorHandler.parseError(NoConnectivityException())
        )
    }

    @Test
    fun parseError_whenUnknownException_matchesMessage() {
        val fakeErrorMessage = "An error occurred"
        val stringId = R.string.generic_error
        every { resourceProvider.getString(stringId) } returns fakeErrorMessage

        assertEquals(
            fakeErrorMessage,
            errorHandler.parseError(Exception())
        )
    }
}
