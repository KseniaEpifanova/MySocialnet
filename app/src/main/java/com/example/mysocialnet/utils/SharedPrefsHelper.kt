package com.example.mysocialnet.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        //private const val KEY_IS_AUTHENTICATED = "is_authenticated"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_USER_PASSWORD = "user_password"
    }

    // Save logged-in state
    fun setLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    // Check logged-in state
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    // Register a new user
    fun registerUser(email: String, password: String) {
        sharedPreferences.edit()
            .putString(KEY_USER_EMAIL, email)
            .putString(KEY_USER_PASSWORD, password)
            .apply()
    }

    // Validate user credentials
    fun validateUser(email: String, password: String): Boolean {
        val storedEmail = sharedPreferences.getString(KEY_USER_EMAIL, null)
        val storedPassword = sharedPreferences.getString(KEY_USER_PASSWORD, null)
        return email == storedEmail && password == storedPassword
    }

    // Check if the user is already registered
    fun isUserRegistered(email: String): Boolean {
        val storedEmail = sharedPreferences.getString(KEY_USER_EMAIL, null)
        return email == storedEmail
    }
}