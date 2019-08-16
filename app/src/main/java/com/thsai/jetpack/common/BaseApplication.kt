package com.thsai.jetpack.common

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho

open class BaseApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        context = this
        Stetho.initializeWithDefaults(this);
    }

    companion object {
        lateinit var context: Context
    }
}