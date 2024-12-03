package com.example.mysocialnet.models

data class ImageResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<Image>
)
