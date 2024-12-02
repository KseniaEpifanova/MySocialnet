package com.example.mysocialnet.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysocialnet.utils.SharedPrefsHelper
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val sharedPrefsHelper: SharedPrefsHelper): ViewModel() {

    private val _isAuthenticated = MutableLiveData<Boolean>()
    val isAuthenticated: LiveData<Boolean> get() = _isAuthenticated

    private val userDatabase = mutableMapOf(
        "test@example.com" to "password123"
    )

    fun loginUser(email: String, password: String) {
        if (userDatabase[email] == password) {
            _isAuthenticated.value = true
            sharedPrefsHelper.setAuthenticated(true)
        } else {
            _isAuthenticated.value = false
        }
    }

    fun checkAuthentication(): Boolean {
        return sharedPrefsHelper.isAuthenticated()
    }

    fun logout() {
        _isAuthenticated.value = false
        sharedPrefsHelper.setAuthenticated(false)
    }
}