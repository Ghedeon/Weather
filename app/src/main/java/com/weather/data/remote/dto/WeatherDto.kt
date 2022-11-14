package com.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("current_weather")
    val current: CurrentWeatherDto,
    @SerialName("hourly")
    val hourly: HourlyWeatherDto,
    @SerialName("daily")
    val daily: DailyWeatherDto
)

@Serializable
data class CurrentWeatherDto(
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("windspeed")
    val windSpeed: Double,
    @SerialName("weathercode")
    val weatherCode: Int
)

@Serializable
data class HourlyWeatherDto(
    @SerialName("time")
    val time: List<String>,
    @SerialName("temperature_2m")
    val temperatures: List<Double>,
    @SerialName("weathercode")
    val weatherCodes: List<Int>,
)

@Serializable
data class DailyWeatherDto(
    @SerialName("time")
    val date: List<String>,
    @SerialName("temperature_2m_max")
    val temperatureMax: List<Double>,
    @SerialName("temperature_2m_min")
    val temperatureMin: List<Double>,
    @SerialName("weathercode")
    val weatherCode: List<Int>
)