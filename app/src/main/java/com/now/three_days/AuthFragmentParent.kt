package com.now.three_days

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.now.three_days.ui.login.LoginViewModel
import com.now.three_days.ui.login.LoginViewModelFactory

open class AuthFragmentParent : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
//    private val loginViewModel:LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)
        val userId = loginViewModel.loginResult.value?.success?.displayName

        findNavController().navigate(R.id.loginFragment)
    }
}