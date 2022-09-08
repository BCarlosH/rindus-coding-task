package com.rinduscodingtask.feature.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ForecastItem(
    day: String,
    hour: String,
    imageUrl: String,
    maxTemperature: String,
    minTemperature: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .width(64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day,
                modifier = Modifier
                    .padding(start = 8.dp, top = 16.dp, end = 8.dp),
                fontSize = 16.sp
            )

            Text(
                text = hour,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp),
                fontSize = 16.sp
            )

            Text(
                text = stringResource(R.string.label_degrees, maxTemperature),
                modifier = Modifier
                    .padding(start = 8.dp, top = 16.dp, end = 8.dp),
                fontSize = 16.sp
            )

            AsyncImage(
                model = imageUrl,
                contentDescription = stringResource(R.string.desc_weather_icon),
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp, end = 8.dp)
            )

            Text(
                text = stringResource(R.string.label_degrees, minTemperature),
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 16.dp),
                fontSize = 16.sp
            )
        }
    }
}
