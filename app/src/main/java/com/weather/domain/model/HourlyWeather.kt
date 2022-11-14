package com.weather.domain.model

import java.time.LocalDateTime

data class HourlyWeather(
    val time: LocalDateTime,
    val temperature: Double,
    val weatherType: WeatherType
)