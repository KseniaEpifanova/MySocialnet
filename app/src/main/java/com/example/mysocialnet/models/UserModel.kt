package com.example.mysocialnet.models

data class UserModel(
    var name: String? = "",
    var email: String? = "",
    var age: Int? = 0,
    var password: String? = "",
    var confirmPassword: String? = ""
)