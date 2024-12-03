package com.example.mysocialnet.models

data class Image(
    val id: Int,
    val url: String,
    val previewURL: String,
    val user: String,
    val likes: Int,
    val downloads: Int,
    val tags: String,
    val imageWidth: Int,
    val imageHeight: Int
)
