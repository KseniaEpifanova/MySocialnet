package com.example.mysocialnet.models

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val id: Int,
    val previewURL: String,
    val user: String,
    val likes: Int,
    val downloads: Int,
    val comments: Int,
    val favorites: Int,
    val views: Int,
    val imageWidth: Int,
    val imageHeight: Int,
    val type: String,
    val tags: String
) : Parcelable, BaseObservable() {

    @get:Bindable
    val displayImageSize: String
        get() = "$imageWidth x $imageHeight"

    @get:Bindable
    val displayLikes: String
        get() = likes.toString()

    @get:Bindable
    val displayDownloads: String
        get() = downloads.toString()

    @get:Bindable
    val displayComments: String
        get() = comments.toString()

    @get:Bindable
    val displayFavorites: String
        get() = favorites.toString()

    @get:Bindable
    val displayViews: String
        get() = views.toString()

    @get:Bindable
    val displayTags: String
        get() = tags.split(",").joinToString(" • ")
}
