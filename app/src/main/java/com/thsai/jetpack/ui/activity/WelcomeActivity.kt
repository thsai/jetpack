package com.thsai.jetpack.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thsai.jetpack.R

class WelcomeActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }
}