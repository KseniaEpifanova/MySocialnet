package com.example.mysocialnet.models

data class UserModel(
    var name: String? = "",
    var email: String? = "",
    var password: String? = "",
    var confirmPassword: String? = ""
)