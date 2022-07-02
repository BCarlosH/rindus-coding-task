package com.example.feature.cats


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.model.Cat
import com.example.core.ui.CardBox
import com.example.core.ui.LoadingScreen
import com.example.core.ui.R
import com.example.core.ui.rememberFlowWithLifecycle

@Composable
fun CatsRoute(
    viewModel: CatsViewModel = hiltViewModel(),
) {
    val catsUiState by rememberFlowWithLifecycle(viewModel.catsUiState)
        .collectAsState(initial = CatsUiState.Loading)

    CatsScreen(catsUiState = catsUiState)
}

@Composable
fun CatsScreen(catsUiState: CatsUiState) {
    when (catsUiState) {
        CatsUiState.Loading -> {
            LoadingScreen()
        }
        is CatsUiState.Success -> {
            CatsContent(
                cats = catsUiState.cats
            )
        }
        is CatsUiState.Error -> {
            Text(
                text = catsUiState.errorMessage,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun CatsContent(cats: List<Cat>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)

    ) {
        items(cats) {
            CardBox(
                iconId = R.drawable.ic_cat,
                text = it.tags.first()
            )
        }
    }
}
