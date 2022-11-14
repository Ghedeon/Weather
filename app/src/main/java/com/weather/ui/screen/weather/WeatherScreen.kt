@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.weather.ui.screen.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.weather.R
import com.weather.ui.screen.weather.WeatherViewModel.UiState
import com.weather.ui.theme.blue_whale

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddings ->
        when (val uiState = viewModel.uiState) {
            UiState.Loading -> Loader()
            is UiState.Data -> Content(Modifier.padding(paddings), uiState)
            is UiState.Error -> Error()
        }
    }
}

@Composable
private fun Content(modifier: Modifier, uiState: UiState.Data) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = modifier
                .graphicsLayer {
                    alpha = 1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 1.5f)
                    translationY = 0.25f * scrollState.value
                }
        ) { CityPhotoHighlight(uiState.city.photoUrl) }
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .offset(y = (-140).dp)
        ) {
            LocationPin(city = uiState.city.name)
            WeatherNow(weather = uiState.weather)
            Text(
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Light,
                text = "Today"
            )

            LazyRow(modifier = Modifier.padding(top = 16.dp)) {
                items(items = uiState.weather.hourly) { weatherData ->
                    TodayHourly(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(110.dp)
                            .padding(end = 16.dp)
                    )
                }
            }
            Text(
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Light,
                text = "Weekly Forecast"
            )
            uiState.weather.daily.forEach { weather ->
                WeekDaily(weather = weather)
            }
        }
    }
}

@Composable
private fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    LottieAnimation(composition, iterations = LottieConstants.IterateForever)
}

@Composable
private fun Error() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(blue_whale),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = null
        )
        Text(
            text = "Ooops!",
            style = MaterialTheme.typography.displayLarge,
            color = Color.White,
            fontWeight = FontWeight.Light
        )
    }
}

