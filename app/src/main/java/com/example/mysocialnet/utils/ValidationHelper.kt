package com.example.mysocialnet.utils

import android.util.Patterns
import com.example.mysocialnet.models.UserModel

object ValidationHelper {

    fun validateName(name: String): String? {
        return if (name.isEmpty()) "Name is required" else null
    }

    fun validateEmail(email: String): String? {
        return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "Invalid email address"
        } else {
            null
        }
    }

    fun validatePassword(password: String): String? {
        return if (password.length !in 6..12) {
            "Password must be 6-12 characters"
        } else {
            null
        }
    }

    fun validateConfirmPassword(password: String?, confirmPassword: String?): String? {
        return if (password != confirmPassword) "Passwords do not match" else null
    }

    fun validateInputs(
        userModel: UserModel
    ): Map<Errors, String> {
        val errors = mutableMapOf<Errors, String>()

        userModel.name?.let {
            validateName(it)?.let { error -> errors[Errors.NAME] = error }
        }

        userModel.email?.let {
            validateEmail(it)?.let { error -> errors[Errors.EMAIL] = error }
        }

        userModel.password?.let {
            validatePassword(it)?.let { error -> errors[Errors.PASSWORD] = error }
        }

        if (!userModel.password.isNullOrEmpty() && !userModel.confirmPassword.isNullOrEmpty()) {
            validateConfirmPassword(userModel.password, userModel.confirmPassword)?.let { error ->
                errors[Errors.CONFIRM_PASSWORD] = error
            }
        }

        return errors
    }

}

enum class Errors{
    NAME,
    EMAIL,
    PASSWORD,
    CONFIRM_PASSWORD
}
