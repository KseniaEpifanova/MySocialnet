package com.example.mysocialnet.features.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnet.R
import com.example.mysocialnet.models.db.UserDB
import com.example.mysocialnet.models.UserModel
import com.example.mysocialnet.utils.Errors
import com.example.mysocialnet.utils.SharedPrefsHelper
import com.example.mysocialnet.utils.ValidationHelper
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val sharedPrefsHelper: SharedPrefsHelper,
    private val realm: Realm
) : ViewModel() {

    private val _fieldErrors = MutableLiveData<Map<Errors, String>>()
    val fieldErrors: LiveData<Map<Errors, String>> get() = _fieldErrors

    private val _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int> get() = _errorMessage


    private var errors = mapOf<Errors, String>()

    fun validateFields(fields: UserModel): Boolean {
        errors = mapOf()
        errors = ValidationHelper.validateInputs(fields)

        _fieldErrors.value = errors
        return errors.isEmpty()
    }

    fun registerUser(username: String?, password: String?) {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) return

        viewModelScope.launch {
            try {
                if (isUserAlreadyRegistered(username)) {
                    _errorMessage.value = R.string.user_already_exists
                    return@launch
                }
                addUserToDB(username, password)
                sharedPrefsHelper.setLoggedIn(true)
            } catch (e: Exception) {
                _errorMessage.value = R.string.something_went_wrong
            }
        }
    }

    private suspend fun addUserToDB(username: String, password: String) {
        return withContext(Dispatchers.IO) {
            realm.write {
                copyToRealm(UserDB().apply {
                    id = java.util.UUID.randomUUID().toString()
                    this.username = username
                    this.password = password
                })
            }
        }
    }

    private suspend fun isUserAlreadyRegistered(username: String): Boolean {
        return withContext(Dispatchers.IO) {
            val isDatabaseEmpty = realm.query<UserDB>().count().find() == 0L

            if (isDatabaseEmpty) {
                return@withContext false
            }
            val user = realm.query<UserDB>("username == $0", username)
                .first()
                .find()
            user != null
        }
    }
}
