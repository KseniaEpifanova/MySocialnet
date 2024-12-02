package com.example.mysocialnet.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_AUTHENTICATED = "is_authenticated"
    }

    fun setAuthenticated(isAuthenticated: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_IS_AUTHENTICATED, isAuthenticated).apply()
    }

    fun isAuthenticated(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_AUTHENTICATED, false)
    }
}