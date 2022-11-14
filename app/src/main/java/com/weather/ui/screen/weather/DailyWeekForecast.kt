package com.weather.ui.screen.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weather.domain.model.DailyWeather
import java.time.format.DateTimeFormatter

@Composable
fun WeekDaily(modifier: Modifier = Modifier, weather: DailyWeather) {
    val dayOfWeek = remember(weather) {
        weather.date.format(
            DateTimeFormatter.ofPattern("EEEE")
        )
    }
    val date = remember(weather) {
        weather.date.format(
            DateTimeFormatter.ofPattern("dd MMM")
        )
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = dayOfWeek
            )
            Text(
                color = MaterialTheme.colorScheme.outline,
                text = date
            )
        }
        Image(
            painter = painterResource(id = weather.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .padding(start = 10.dp)
        )
        Text(
            modifier = Modifier
                .padding(start = 24.dp)
                .width(50.dp),
            textAlign = TextAlign.End,
            fontSize = 18.sp,
            text = "${weather.temperatureMin}°",
            fontWeight = FontWeight.Light
        )
        Text(
            modifier = Modifier
                .padding(start = 24.dp)
                .width(50.dp),
            textAlign = TextAlign.End,
            fontSize = 18.sp,
            text = "${weather.temperatureMax}°",
            fontWeight = FontWeight.Light
        )
    }
}
