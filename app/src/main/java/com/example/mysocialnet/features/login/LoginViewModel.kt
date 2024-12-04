package com.example.mysocialnet.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnet.models.db.UserDB
import com.example.mysocialnet.utils.ValidationHelper
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val realm: Realm
) :
    ViewModel() {

    private val _isAuthenticated = MutableLiveData<Boolean>()
    val isAuthenticated: LiveData<Boolean> get() = _isAuthenticated

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private suspend fun isUserExist(username: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val isDatabaseEmpty = realm.query<UserDB>().count().find() == 0L

            if (isDatabaseEmpty) {
                return@withContext false
            }
            val user = realm.query<UserDB>("username == $0 AND password == $1", username, password)
                .first()
                .find()
            user != null
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val isAuthenticated = isUserExist(email, password)
                _isAuthenticated.value = isAuthenticated
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun validateInput(email: String, password: String): Boolean {
        return ValidationHelper.validateEmail(email)
            .isNullOrEmpty() && ValidationHelper.validatePassword(password).isNullOrEmpty()
    }
}
