package com.thsai.jetpack.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thsai.jetpack.db.repository.UserRepository
import com.thsai.jetpack.viewmodel.LoginModel

class LoginModelFactory(private val userRepository: UserRepository, private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginModel(userRepository) as T
    }
}