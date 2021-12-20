package com.now.three_days.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.now.three_days.MainActivity
import com.now.three_days.R
import com.now.three_days.data.LoginDataSource
import com.now.three_days.data.LoginRepository
import com.now.three_days.ui.login.LoginViewModel
import com.now.three_days.ui.login.LoginViewModelFactory

open class AuthFragmentParent : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = LoginViewModel(
                LoginRepository(dataSource = LoginDataSource())
            ) as T
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mainAct = activity as MainActivity
        val userFile = mainAct.getFile()

        if (!userFile.userLog()) {
            findNavController().navigate(R.id.loginFragment)
        }
    }
}