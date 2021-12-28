package com.now.three_days.data

import android.util.Log
import com.now.three_days.data.model.LoggedInUser
import com.now.three_days.data.model.User
import com.now.three_days.data.viewmodel.UserViewModel
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource() {

//    private val userService: UserServiceImplV1
//
//    private lateinit var fireServiceStore: FireServiceStore

    private var userViewModel: UserViewModel? = null

    init {
//        userService = UserServiceImplV1()
        userViewModel = UserViewModel()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        val userDataList = userViewModel?.users
//        Log.d("user data from firebase", userDataList.toString())
        val list = userDataList?.value
//        Log.d("user list from data", list.toString())

        val findUserId = list?.filter { it.userId == username }
        if (findUserId.isNullOrEmpty())
            return Result.Success(join(username, password))
        try {
            val user = findUserId[0]
            if (user.password != password)
                return Result.Error(IOException("Error logging in"))
            val loggedInUser =
                LoggedInUser(
                    user?.userId,
                    user?.nikname, user?.password,
                    user?.role,
                    user?.email, true
                )
            return Result.Success(loggedInUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
//        return Result.Error(IOException("Error logging in"))
    }

    fun loginTestOne(username: String, password: String): Result<LoggedInUser> {
//        userViewModel?.getUsersFirebase()

        val userDataList = userViewModel?.dataFromFireBase()
        Log.d("user data from firebase", userDataList.toString())
        val list = userDataList?.value
        Log.d("user list from data", list.toString())

        return Result.Error(IOException("Error logging in"))
    }

    fun join(username: String, password: String): LoggedInUser {
        val email = String.format("%s@naver.com", username)
        val user = User(username, username, password, "0", email)
        userViewModel?.insert(user, "user")
        return LoggedInUser(user.userId, user.nikname, user.password, user.role, user.email, true)
    }

    fun logout() {
        // TODO: revoke authentication

    }
}