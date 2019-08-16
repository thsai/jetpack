package com.thsai.jetpack.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.thsai.jetpack.R
import com.thsai.jetpack.common.BaseConstant
import com.thsai.jetpack.databinding.FragmentRegisterBinding
import com.thsai.jetpack.viewmodel.CustomViewModelProvider
import com.thsai.jetpack.viewmodel.RegisterModel

class RegisterFragment : Fragment() {
    private val viewModel: RegisterModel by viewModels {
        CustomViewModelProvider.providerRegisterModel(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentRegisterBinding.inflate(inflater, container, false)
        initData(binding)
        onSubscribeUI(binding)
        return binding.root
    }

    private fun initData(binding: FragmentRegisterBinding) {
        //safeArgs使用
        val safeArgs: RegisterFragmentArgs by navArgs()
        val email = safeArgs.email
        binding.model = viewModel
        binding.model?.email?.value = email
        binding.activity = activity
    }

    private fun onSubscribeUI(binding: FragmentRegisterBinding) {
        binding.btnRegister.setOnClickListener {
            viewModel.register()
            Toast.makeText(requireContext(), "注册成功", Toast.LENGTH_SHORT).show()
            val bundle = Bundle()
            bundle.putString(BaseConstant.ARGS_NAME, viewModel.name.value)
            findNavController().navigate(R.id.login_fragment, bundle)
        }
        viewModel.email.observe(viewLifecycleOwner, Observer {
            binding.isEnable =
                it.isNotEmpty() && viewModel.name.value!!.isNotEmpty() && viewModel.pwd.value!!.isNotEmpty()
        })
        viewModel.pwd.observe(viewLifecycleOwner, Observer {
            binding.isEnable =
                it.isNotEmpty() && viewModel.name.value!!.isNotEmpty() && viewModel.email.value!!.isNotEmpty()
        })
        viewModel.name.observe(viewLifecycleOwner, Observer {
            binding.isEnable =
                it.isNotEmpty() && viewModel.pwd.value!!.isNotEmpty() && viewModel.email.value!!.isNotEmpty()
        })

    }
}

