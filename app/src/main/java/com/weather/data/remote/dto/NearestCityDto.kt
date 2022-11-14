package com.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NearestCityDto(
    @SerialName("_embedded")
    val embedded: EmbeddedDto
)

@Serializable
data class EmbeddedDto(
    @SerialName("location:nearest-urban-areas")
    val nearestCities: List<CitiesDto>,
)

@Serializable
data class CitiesDto(
    @SerialName("_links")
    val links: LinksDto,
)

@Serializable
data class LinksDto(
    @SerialName("location:nearest-urban-area")
    val city: CityDto
)

@Serializable
data class CityDto(
    @SerialName("href")
    val href: String,
    @SerialName("name")
    val name: String
)