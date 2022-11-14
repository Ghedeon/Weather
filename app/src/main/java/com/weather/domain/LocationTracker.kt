package com.weather.domain

import android.location.Location
import com.github.michaelbull.result.Result
import com.weather.domain.model.DomainError

interface LocationTracker {
    suspend fun getCurrentLocation(): Result<Location, DomainError>
}