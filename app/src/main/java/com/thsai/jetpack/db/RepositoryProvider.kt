package com.thsai.jetpack.db

import android.content.Context
import com.thsai.jetpack.db.repository.ShoeRepository
import com.thsai.jetpack.db.repository.UserRepository

object RepositoryProvider {
    fun providerUserRepository(context: Context): UserRepository {
        return UserRepository.getInstance(AppDataBase.getInstance(context).userDao())
    }

    fun providerShoeRepository(context: Context):ShoeRepository{
        return ShoeRepository.getInstance(AppDataBase.getInstance(context).shoeDao())
    }
}