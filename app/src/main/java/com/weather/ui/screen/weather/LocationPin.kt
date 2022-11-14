package com.weather.ui.screen.weather

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.weather.R

@Composable
fun LocationPin(city: String) {
    Row(
        modifier = Modifier.fillMaxWidth(.7f),
        verticalAlignment = Alignment.Bottom
    ) {
        Icon(
            modifier = Modifier.padding(bottom = 10.dp, end = 6.dp),
            painter = painterResource(id = R.drawable.outline_location_on_24), contentDescription = null
        )
        Text(
            text = city,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.displaySmall,
        )
    }
}