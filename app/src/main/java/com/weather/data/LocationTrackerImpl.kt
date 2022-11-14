package com.weather.data

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat.checkSelfPermission
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.google.android.gms.location.FusedLocationProviderClient
import com.weather.domain.LocationTracker
import com.weather.domain.model.DomainError
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationTrackerImpl @Inject constructor(
    private val locationProvider: FusedLocationProviderClient,
    private val locationManager: LocationManager,
    private val application: Application
) : LocationTracker {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Result<Location, DomainError> = withPermissions {
        suspendCancellableCoroutine { continuation ->
            locationProvider.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) continuation.resume(Ok(result)) else continuation.resume(Err(DomainError))
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener { continuation.resume(Ok(result)) }
                addOnFailureListener { continuation.resume(Err(DomainError)) }
                addOnCanceledListener { continuation.cancel() }
            }
        }
    }

    private suspend fun withPermissions(block: suspend () -> Result<Location, DomainError>): Result<Location, DomainError> {
        val hasCoarseLocationPermission = checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!hasCoarseLocationPermission || !isGpsEnabled) return Err(DomainError)

        return block()
    }
}