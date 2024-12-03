package com.example.mysocialnet.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnet.models.Image
import com.example.mysocialnet.models.ImageResponse
import com.example.mysocialnet.repositories.ImageRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _imageList = MutableLiveData<List<Image>>()
    val imageList: LiveData<List<Image>> get() = _imageList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchImages(page: Int, perPage: Int) {

        viewModelScope.launch {
            try {
                val response: Response<ImageResponse> = imageRepository.getImages(page, perPage)
                if (response.isSuccessful) {
                    val currentList = _imageList.value.orEmpty()
                    val newImages = response.body()?.hits.orEmpty()
                    _imageList.value = currentList + newImages
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
            }
        }
    }
}
