package com.weather.data.remote

import com.slack.eithernet.ApiResult
import com.weather.data.remote.dto.NearestCityDto
import com.weather.data.remote.dto.PhotosDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface CityApi {

    @GET("locations/{coordinates}")
    suspend fun getNearestCity(@Path("coordinates") coordinates: String): ApiResult<NearestCityDto, ApiError>

    @GET
    suspend fun getCityPhotos(@Url url: String): ApiResult<PhotosDto, ApiError>
}