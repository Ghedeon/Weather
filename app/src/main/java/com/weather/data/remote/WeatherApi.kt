package com.weather.data.remote

import com.slack.eithernet.ApiResult
import com.weather.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast?hourly=temperature_2m,weathercode&current_weather=true&daily=temperature_2m_max,temperature_2m_min,weathercode&timezone=auto")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): ApiResult<WeatherDto, ApiError>
}