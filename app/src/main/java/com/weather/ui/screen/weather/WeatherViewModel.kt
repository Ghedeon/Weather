package com.weather.ui.screen.weather

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.coroutines.binding.binding
import com.github.michaelbull.result.onFailure
import com.weather.domain.LocationTracker
import com.weather.domain.Repository
import com.weather.domain.model.City
import com.weather.domain.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MissingPermission")
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
    private val repository: Repository
) : ViewModel() {

    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        viewModelScope.launch {
            binding {
                val location = locationTracker.getCurrentLocation().bind()
                val city = async { repository.getCurrentCity(location.latitude, location.longitude).bind() }
                val weather = async { repository.getWeather(location.latitude, location.longitude).bind() }
                uiState = UiState.Data(city.await(), weather.await())
            }.onFailure {
                uiState = UiState.Error
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        object Error : UiState()
        data class Data(val city: City, val weather: Weather) : UiState()
    }
}