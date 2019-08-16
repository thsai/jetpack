package com.thsai.jetpack

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment2.*

class Fragment2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn2.setOnClickListener {
            //返回fragment1
            Navigation.findNavController(it).navigateUp()
        }
        btn1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_to_page3)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("nav","Fragment2  onDestroy")
    }
}