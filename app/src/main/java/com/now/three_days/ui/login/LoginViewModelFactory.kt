package com.now.three_days.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.now.three_days.data.LoginDataSource
import com.now.three_days.data.LoginRepository
import com.now.three_days.service.impl.UserServiceImplV1

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource(UserServiceImplV1())
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}