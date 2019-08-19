package com.thsai.jetpack.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.joe.jetpackdemo.utils.AppPrefsUtils
import com.thsai.jetpack.R
import com.thsai.jetpack.common.BaseConstant
import com.thsai.jetpack.databinding.FragmentLoginBinding
import com.thsai.jetpack.ui.activity.MainActivity
import com.thsai.jetpack.viewmodel.CustomViewModelProvider
import com.thsai.jetpack.viewmodel.LoginModel

class LoginFragment : Fragment() {
    private val loginModel: LoginModel by viewModels {
        CustomViewModelProvider.providerLoginModel(requireContext())
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )
        onSubscribeUI(binding)
        return binding.root
    }

    private fun onSubscribeUI(binding: FragmentLoginBinding) {
        binding.activity = activity
        binding.model = loginModel

        binding.btnLogin.setOnClickListener {
            loginModel.login().observe(viewLifecycleOwner, Observer { user ->
                user?.apply {
                    AppPrefsUtils.putLong(BaseConstant.SP_USER_ID, id)
                    AppPrefsUtils.putString(BaseConstant.SP_USER_NAME, acccount)
                    val intent = Intent(context, MainActivity::class.java)
                    requireContext().startActivity(intent)
                    Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show()
                } ?: Toast.makeText(context, "登陆失败", Toast.LENGTH_SHORT).show()
            })
        }

        loginModel.p.observe(viewLifecycleOwner, Observer {
            binding.isEnable = it.isNotEmpty() && loginModel.n.value!!.isNotEmpty()
        })

        val name = arguments?.getString(BaseConstant.ARGS_NAME)
        name?.let {
            loginModel.n.value = it
        }
    }
}
