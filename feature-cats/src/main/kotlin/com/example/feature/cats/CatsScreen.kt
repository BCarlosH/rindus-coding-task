package com.example.feature.cats


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.model.Cat
import com.example.core.ui.*
import com.example.core.ui.R

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
    Scaffold(
        topBar = { CatsTopAppBar(title = "Cats") }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
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
}

@Preview(showBackground = true)
@Composable
fun CatsContentPreview() {
    CatsContent(
        cats = listOf(
            Cat(id = "1", createdAt = "1", tags = listOf("cute"), url = "1"),
            Cat(id = "2", createdAt = "2", tags = listOf("fun"), url = "2"),
            Cat(id = "3", createdAt = "3", tags = listOf("running"), url = "3"),
            Cat(id = "4", createdAt = "4", tags = listOf("box"), url = "4")
        ),
        onCatClick = {})
}
