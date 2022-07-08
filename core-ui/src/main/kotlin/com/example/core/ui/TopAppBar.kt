package com.example.core.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
fun CatsTopAppBar(
    title: String,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    icon: @Composable (() -> Unit)? = null,
    onBackClick: () -> Unit = {},
) {
    val navigationIcon: (@Composable () -> Unit)? = if (icon == null) {
        null
    } else {
        { IconButton(onClick = onBackClick) { icon() } }
    }

    TopAppBar(
        title = { Text(title, fontSize = 18.sp) },
        navigationIcon = navigationIcon,
        elevation = elevation
    )
}

@Preview
@Composable
fun SimpleTopBarPreview() {
    CatsTopAppBar(
        title = "CatsTopAppBar",
        icon = { Icon(Icons.Filled.ArrowBack, "Back") }
    )
}
