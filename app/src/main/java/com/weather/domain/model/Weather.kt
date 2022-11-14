package com.weather.domain.model

data class Weather(
    val temperature: Double,
    val windSpeed: Double,
    val weatherType: WeatherType,
    val hourly: List<HourlyWeather>,
    val daily: List<DailyWeather>
)