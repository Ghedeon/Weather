package com.weather.domain.model

import androidx.annotation.DrawableRes
import com.weather.R

sealed class WeatherType(
    val name: String,
    @DrawableRes val iconRes: Int
) {
    object ClearSky : WeatherType(
        name = "Clear sky",
        iconRes = R.drawable.ic_sunny
    )

    object MainlyClear : WeatherType(
        name = "Mainly clear",
        iconRes = R.drawable.ic_cloudy
    )

    object PartlyCloudy : WeatherType(
        name = "Partly cloudy",
        iconRes = R.drawable.ic_cloudy
    )

    object Overcast : WeatherType(
        name = "Overcast",
        iconRes = R.drawable.ic_cloudy
    )

    object Foggy : WeatherType(
        name = "Foggy",
        iconRes = R.drawable.ic_very_cloudy
    )

    object DepositingRimeFog : WeatherType(
        name = "Depositing rime fog",
        iconRes = R.drawable.ic_very_cloudy
    )

    object LightDrizzle : WeatherType(
        name = "Light drizzle",
        iconRes = R.drawable.ic_rainshower
    )

    object ModerateDrizzle : WeatherType(
        name = "Moderate drizzle",
        iconRes = R.drawable.ic_rainshower
    )

    object DenseDrizzle : WeatherType(
        name = "Dense drizzle",
        iconRes = R.drawable.ic_rainshower
    )

    object LightFreezingDrizzle : WeatherType(
        name = "Slight freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy
    )

    object DenseFreezingDrizzle : WeatherType(
        name = "Dense freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy
    )

    object SlightRain : WeatherType(
        name = "Slight rain",
        iconRes = R.drawable.ic_rainy
    )

    object ModerateRain : WeatherType(
        name = "Rainy",
        iconRes = R.drawable.ic_rainy
    )

    object HeavyRain : WeatherType(
        name = "Heavy rain",
        iconRes = R.drawable.ic_rainy
    )

    object HeavyFreezingRain : WeatherType(
        name = "Heavy freezing rain",
        iconRes = R.drawable.ic_snowyrainy
    )

    object SlightSnowFall : WeatherType(
        name = "Slight snow fall",
        iconRes = R.drawable.ic_snowy
    )

    object ModerateSnowFall : WeatherType(
        name = "Moderate snow fall",
        iconRes = R.drawable.ic_heavysnow
    )

    object HeavySnowFall : WeatherType(
        name = "Heavy snow fall",
        iconRes = R.drawable.ic_heavysnow
    )

    object SnowGrains : WeatherType(
        name = "Snow grains",
        iconRes = R.drawable.ic_heavysnow
    )

    object SlightRainShowers : WeatherType(
        name = "Slight rain showers",
        iconRes = R.drawable.ic_rainshower
    )

    object ModerateRainShowers : WeatherType(
        name = "Moderate rain showers",
        iconRes = R.drawable.ic_rainshower
    )

    object ViolentRainShowers : WeatherType(
        name = "Violent rain showers",
        iconRes = R.drawable.ic_rainshower
    )

    object SlightSnowShowers : WeatherType(
        name = "Light snow showers",
        iconRes = R.drawable.ic_snowy
    )

    object HeavySnowShowers : WeatherType(
        name = "Heavy snow showers",
        iconRes = R.drawable.ic_snowy
    )

    object ModerateThunderstorm : WeatherType(
        name = "Moderate thunderstorm",
        iconRes = R.drawable.ic_thunder
    )

    object SlightHailThunderstorm : WeatherType(
        name = "Thunderstorm with slight hail",
        iconRes = R.drawable.ic_rainythunder
    )

    object HeavyHailThunderstorm : WeatherType(
        name = "Thunderstorm with heavy hail",
        iconRes = R.drawable.ic_rainythunder
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when (code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}