package com.rinduscodingtask.feature.caweb

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebView
import com.google.accompanist.web.WebViewState
import com.google.accompanist.web.rememberWebViewState
import com.rinduscodingtask.core.ui.LoadingScreen

private const val C_AND_A_URL = "https://www.c-and-a.com/de/de/shop"

@Composable
fun CaWebRoute() {
    val state = rememberWebViewState(C_AND_A_URL)

    CaWebScreen(
        state = state
    )
}

@Composable
private fun CaWebScreen(
    state: WebViewState
) {
    Column {
        if (state.isLoading) {
            LoadingScreen(
                modifier = Modifier.fillMaxSize()
            )
        }

        WebView(
            state = state,
            modifier = Modifier.fillMaxSize(),
            onCreated = { it.settings.javaScriptEnabled = true }
        )
    }
}
