@file:OptIn(ExperimentalSerializationApi::class)
@file:Suppress("JSON_FORMAT_REDUNDANT")

package com.weather.data

import android.app.Application
import android.content.Context
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.slack.eithernet.ApiResultCallAdapterFactory
import com.slack.eithernet.ApiResultConverterFactory
import com.weather.BuildConfig
import com.weather.data.remote.CityApi
import com.weather.data.remote.WeatherApi
import com.weather.domain.LocationTracker
import com.weather.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideRepository(impl: RepositoryImpl): Repository

    @Binds
    abstract fun provideLocationTracker(impl: LocationTrackerImpl): LocationTracker

    companion object {

        @Singleton
        @Provides
        fun provideCityApi(@Named("city") retrofit: Retrofit): CityApi =
            retrofit.create(CityApi::class.java)

        @Singleton
        @Provides
        fun provideWeatherApi(@Named("weather") retrofit: Retrofit): WeatherApi =
            retrofit.create(WeatherApi::class.java)

        @Named("weather")
        @Singleton
        @Provides
        fun providesWeatherRetrofit(okHttpClient: OkHttpClient, jsonConverter: Converter.Factory): Retrofit =
            Retrofit.Builder().baseUrl(BuildConfig.OPEN_METEO_API_URL)
                .client(okHttpClient)
                .addConverterFactory(ApiResultConverterFactory)
                .addCallAdapterFactory(ApiResultCallAdapterFactory)
                .addConverterFactory(jsonConverter)
                .build()

        @Named("city")
        @Singleton
        @Provides
        fun providesCityRetrofit(okHttpClient: OkHttpClient, jsonConverter: Converter.Factory): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.TELEPORT_API_URL)
                .client(okHttpClient)
                .addConverterFactory(ApiResultConverterFactory)
                .addCallAdapterFactory(ApiResultCallAdapterFactory)
                .addConverterFactory(jsonConverter)
                .build()

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

        @Singleton
        @Provides
        fun providesJsonConverter(): Converter.Factory {
            val contentType = "application/json".toMediaType()
            return Json { ignoreUnknownKeys = true }.asConverterFactory(contentType)
        }

        @Singleton
        @Provides
        fun provideLocationProvider(app: Application): FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(app)

        @Singleton
        @Provides
        fun provideLocationManager(app: Application): LocationManager =
            app.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
}