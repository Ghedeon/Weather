package com.weather.data

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.map
import com.github.michaelbull.result.mapError
import com.slack.eithernet.ApiResult
import com.weather.data.remote.ApiError
import com.weather.data.remote.CityApi
import com.weather.data.remote.WeatherApi
import com.weather.data.remote.dto.CityDto
import com.weather.domain.Repository
import com.weather.domain.model.City
import com.weather.domain.model.DomainError
import com.weather.domain.model.Weather
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val cityApi: CityApi,
    private val weatherApi: WeatherApi
) : Repository {

    override suspend fun getCurrentCity(lat: Double, long: Double): Result<City, DomainError> =
        getNearestCity(lat, long).flatMap { city ->
            getCityPhotoUrl(cityUrl = city.href)
                .map { photoUrl ->
                    City(name = city.name, photoUrl = photoUrl)
                }
        }.mapError { DomainError }

    override suspend fun getWeather(lat: Double, long: Double): Result<Weather, DomainError> =
        when (val weatherResult = weatherApi.getWeather(lat, long)) {
            is ApiResult.Success -> Ok(weatherResult.value.toDomain())
            else -> Err(DomainError)
        }

    private suspend fun getNearestCity(lat: Double, long: Double): Result<CityDto, ApiError> =
        when (val cityResult = cityApi.getNearestCity("$lat,$long")) {
            is ApiResult.Success -> Ok(cityResult.value.embedded.nearestCities.first().links.city)
            else -> Err(ApiError)
        }

    private suspend fun getCityPhotoUrl(cityUrl: String): Result<String, ApiError> =
        when (val photoResult = cityApi.getCityPhotos("${cityUrl}images")) {
            is ApiResult.Success -> Ok(photoResult.value.photos.first().image.mobile)
            else -> Err(ApiError)
        }
}