package com.example.feature.cats.catImage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.core.ui.LoadingScreen
import com.example.core.ui.rememberFlowWithLifecycle

@Composable
fun CatImageRoute(
    viewModel: CatImageViewModel = hiltViewModel(),
    catId: String,
) {
    LaunchedEffect(Unit) {
        viewModel.fetchCatById(catId)
    }

    val catImageUiState by rememberFlowWithLifecycle(viewModel.catImageUiState)
        .collectAsState(initial = CatImageUiState.Loading)

    CatImageScreen(catImageUiState = catImageUiState)
}

@Composable
fun CatImageScreen(catImageUiState: CatImageUiState) {
    when (catImageUiState) {
        is CatImageUiState.Loading -> {
            LoadingScreen()
        }
        is CatImageUiState.Success -> {
            CatImageContent(url = catImageUiState.cat.url)
        }
        is CatImageUiState.Error -> {
            Text(
                text = catImageUiState.errorMessage,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun CatImageContent(url: String) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = url,
            contentDescription = null
        )
    }
}
