package com.example.ktprojectfortuturu.repository.network

import com.example.ktprojectfortuturu.BuildConfig
import com.example.ktprojectfortuturu.repository.model.AstroPicturesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AstroAPI {

    @GET("/planetary/apod")
    suspend fun getAstroPictures(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
        @Query("start_date")
        startDate: String = "2022-03-10"
    ): Response<List<AstroPicturesDTO>>

    @GET("/planetary/apod")
    suspend fun getAstroPictureOfTheDay(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY,
    ): Response<List<AstroPicturesDTO>>

}