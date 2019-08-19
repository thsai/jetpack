package com.thsai.jetpack.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thsai.jetpack.common.listener.SimpleWatcher
import com.thsai.jetpack.db.repository.UserRepository
import kotlinx.coroutines.launch

class RegisterModel constructor(private val userRepository: UserRepository) : ViewModel() {
    val email = MutableLiveData("")
    val name = MutableLiveData("")
    val pwd = MutableLiveData("")

    fun register() {
        viewModelScope.launch {
            userRepository.register(name.value!!, pwd.value!!, email.value!!)
        }
    }

    val nameWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            name.value = s.toString()
        }
    }

    val pwdWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            pwd.value = s.toString()
        }
    }

    fun onEmailChanged(s: CharSequence) {
        email.value = s.toString()
    }
}