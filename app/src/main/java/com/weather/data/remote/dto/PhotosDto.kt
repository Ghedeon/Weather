package com.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotosDto(
    @SerialName("photos")
    val photos: List<PhotoDto>
)

@Serializable
data class PhotoDto(
    @SerialName("image")
    val image: ImageDto
)

@Serializable
data class ImageDto(
    @SerialName("mobile")
    val mobile: String
)

