package com.thsai.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.joe.jetpackdemo.utils.AppPrefsUtils
import com.thsai.jetpack.R
import com.thsai.jetpack.common.BaseConstant
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login.setOnClickListener {
            val name = AppPrefsUtils.getString(BaseConstant.USER_NAME)
            val bundle = Bundle()
            bundle.putString(BaseConstant.ARGS_NAME, name)
            findNavController().navigate(R.id.welcom_to_login, bundle)
        }
        btn_register.setOnClickListener {
            val email = WelcomeFragmentDirections.welcomToRegister().setEmail("12345@qq.com")
            findNavController().navigate(email)
        }
    }
}