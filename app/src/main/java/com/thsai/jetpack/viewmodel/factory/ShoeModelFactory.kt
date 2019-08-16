package com.thsai.jetpack.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thsai.jetpack.db.repository.ShoeRepository
import com.thsai.jetpack.viewmodel.ShoeModel

class ShoeModelFactory(private val shoeRepository: ShoeRepository, private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoeModel(shoeRepository) as T
    }
}