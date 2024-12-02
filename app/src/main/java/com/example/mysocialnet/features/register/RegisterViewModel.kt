package com.example.mysocialnet.features.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysocialnet.models.UserModel
import com.example.mysocialnet.utils.Errors
import com.example.mysocialnet.utils.SharedPrefsHelper
import com.example.mysocialnet.utils.ValidationHelper
import javax.inject.Inject

class RegisterViewModel@Inject constructor(private val sharedPrefsHelper: SharedPrefsHelper) : ViewModel() {

    private val _fieldErrors = MutableLiveData<Map<Errors, String>>()
    val fieldErrors: LiveData<Map<Errors, String>> get() = _fieldErrors

    private val _isRegistered = MutableLiveData<Boolean>()
    val isRegistered: LiveData<Boolean> get() = _isRegistered

    // Mock database
    private val userDatabase = mutableMapOf<String, String>()
    private var errors = mapOf<Errors, String>()

    var userModel = UserModel()

    fun validateFields(fields: UserModel): Boolean {
        errors = mapOf()
        errors = ValidationHelper.validateInputs(fields)

        _fieldErrors.value = errors
        return errors.isEmpty()
    }

    fun isUserAlreadyRegistered(email: String?): Boolean {
        return userDatabase.containsKey(email)
    }

    fun registerUser(email: String?, password: String?) {
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            userDatabase[email] = password
            _isRegistered.value = true
            sharedPrefsHelper.setAuthenticated(true)
        } else {
            _isRegistered.value = false
        }
    }
}
