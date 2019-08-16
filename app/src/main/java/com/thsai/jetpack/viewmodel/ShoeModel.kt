package com.thsai.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import com.thsai.jetpack.db.repository.ShoeRepository

class ShoeModel constructor(private val shoeRepository: ShoeRepository):ViewModel() {
}