package com.example.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardBox(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    text: String,
) {
    Card(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "Cat icon",
                modifier = Modifier.align(Alignment.CenterStart),
                tint = Color.Unspecified
            )
            Text(
                text = text,
                style = MaterialTheme.typography.h4,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun CardBoxPreview() {
    CardBox(
        iconId = R.drawable.ic_cat,
        text = "CardBox"
    )
}
