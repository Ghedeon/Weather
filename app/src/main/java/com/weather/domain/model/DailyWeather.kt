package com.weather.domain.model

import java.time.LocalDate

data class DailyWeather(
    val date: LocalDate,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val weatherType: WeatherType
)