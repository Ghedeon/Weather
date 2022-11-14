package com.weather.data

import com.weather.data.remote.dto.WeatherDto
import com.weather.domain.model.DailyWeather
import com.weather.domain.model.HourlyWeather
import com.weather.domain.model.Weather
import com.weather.domain.model.WeatherType.Companion.fromWMO
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDto.toDomain() = Weather(
    temperature = current.temperature,
    windSpeed = current.windSpeed,
    weatherType = fromWMO(current.weatherCode),
    hourly = hourly.time.take(HOURS_IN_DAY)
        .mapIndexed { idx, time ->
            HourlyWeather(
                time = time.asDateTime(),
                temperature = hourly.temperatures[idx],
                weatherType = fromWMO(hourly.weatherCodes[idx])
            )
        },
    daily = daily.date.mapIndexed { idx, date ->
        DailyWeather(
            date = date.asDate(),
            temperatureMin = daily.temperatureMin[idx],
            temperatureMax = daily.temperatureMax[idx],
            weatherType = fromWMO(daily.weatherCode[idx])
        )
    }
)

private fun String.asDateTime() = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)

private fun String.asDate() = LocalDate.parse(this, DateTimeFormatter.ISO_DATE)

private const val HOURS_IN_DAY = 24