package com.example.mysocialnet.repositories

import com.example.mysocialnet.models.ImageResponse
import com.example.mysocialnet.services.ApiService
import com.example.mysocialnet.utils.Constants
import retrofit2.Response
import javax.inject.Inject

class ImageRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getImages(page: Int, perPage: Int): Response<ImageResponse> {
        return apiService.getImages(Constants.API_KEY, page, perPage, true)
    }
}
