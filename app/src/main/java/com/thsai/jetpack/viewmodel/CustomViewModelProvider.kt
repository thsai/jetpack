package com.thsai.jetpack.viewmodel

import android.content.Context
import com.thsai.jetpack.db.RepositoryProvider
import com.thsai.jetpack.viewmodel.factory.LoginModelFactory
import com.thsai.jetpack.viewmodel.factory.RegisterModelFactory
import com.thsai.jetpack.viewmodel.factory.ShoeModelFactory

object CustomViewModelProvider {

    fun providerLoginModel(context: Context): LoginModelFactory {
        val providerUserRepository = RepositoryProvider.providerUserRepository(context)
        return LoginModelFactory(providerUserRepository, context)
    }

    fun providerRegisterModel(context: Context): RegisterModelFactory {
        val providerUserRepository = RepositoryProvider.providerUserRepository(context)
        return RegisterModelFactory(providerUserRepository, context)
    }

    fun providerShoeModel(context: Context): ShoeModelFactory {
        val providerShoeRepository = RepositoryProvider.providerShoeRepository(context)
        return ShoeModelFactory(providerShoeRepository, context)
    }
}