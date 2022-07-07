package com.example.feature.cats


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
    onCatClick: (String) -> Unit,
) {
    val catsUiState by rememberFlowWithLifecycle(viewModel.catsUiState)
        .collectAsState(initial = CatsUiState.Loading)

    CatsScreen(
        catsUiState = catsUiState,
        onCatClick = onCatClick
    )
}

@Composable
fun CatsScreen(
    catsUiState: CatsUiState,
    onCatClick: (String) -> Unit,
) {
    when (catsUiState) {
        is CatsUiState.Loading -> {
            LoadingScreen()
        }
        is CatsUiState.Success -> {
            CatsContent(
                cats = catsUiState.cats,
                onCatClick = onCatClick
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
fun CatsContent(
    cats: List<Cat>,
    onCatClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)

    ) {
        items(cats) {
            CardBox(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onCatClick(it.id) },
                iconId = R.drawable.ic_cat,
                text = it.tags.first()
            )
        }
    }
}
