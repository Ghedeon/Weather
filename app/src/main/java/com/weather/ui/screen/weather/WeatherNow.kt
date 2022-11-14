package com.weather.ui.screen.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.weather.R
import com.weather.domain.model.Weather

@Composable
fun WeatherNow(weather: Weather) {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            text = "${weather.temperature}°",
            style = MaterialTheme.typography.displayLarge
        )
        Column(modifier = Modifier.padding(start = 24.dp, bottom = 8.dp)) {
            Row {
                Image(
                    modifier = Modifier
                        .size(24.dp, 24.dp)
                        .padding(end = 6.dp),
                    painter = painterResource(id = weather.weatherType.iconRes), contentDescription = null
                )
                Text(text = weather.weatherType.name)
            }
            Row {
                Image(
                    modifier = Modifier
                        .size(24.dp, 24.dp)
                        .padding(end = 6.dp),
                    painter = painterResource(id = R.drawable.ic_wind), contentDescription = null
                )
                Text(text = "${weather.windSpeed} km/h")
            }
        }
    }
}