package com.thsai.jetpack.viewmodel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thsai.jetpack.common.listener.SimpleWatcher
import com.thsai.jetpack.db.data.User
import com.thsai.jetpack.db.repository.UserRepository

class LoginModel constructor(private val userRepository: UserRepository) : ViewModel() {
    val p = MutableLiveData<String>("")
    val n = MutableLiveData<String>("")

    fun onNameChanged(s: CharSequence) {
        n.value = s.toString()
    }

    fun onPwdChanged(s: CharSequence) {
        p.value = s.toString()
    }

    fun login(): LiveData<User?> {
        return userRepository.login(n.value!!, p.value!!)
    }

    val nameWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            n.value = s.toString()
        }
    }

    val pwdWatcher = object : SimpleWatcher() {
        override fun afterTextChanged(s: Editable) {
            super.afterTextChanged(s)
            p.value = s.toString()
        }
    }

}