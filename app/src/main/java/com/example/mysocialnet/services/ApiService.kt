package com.example.mysocialnet.services

import com.example.mysocialnet.models.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api")
    suspend fun getImages(
        @Query("key") apiKey: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("photo") photo: Boolean
    ): Response<ImageResponse>

}
