package com.gzeinnumer.junit4part7retrofithiltsetup.data.remote

import com.gzeinnumer.junit4part7retrofithiltsetup.BuildConfig
import com.gzeinnumer.junit4part7retrofithiltsetup.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ImageResponse>
}