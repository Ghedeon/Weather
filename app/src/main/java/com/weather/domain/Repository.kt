package com.weather.domain

import com.github.michaelbull.result.Result
import com.weather.domain.model.City
import com.weather.domain.model.DomainError
import com.weather.domain.model.Weather

interface Repository {

    suspend fun getCurrentCity(lat: Double, long: Double): Result<City, DomainError>
    suspend fun getWeather(lat: Double, long: Double): Result<Weather, DomainError>
}