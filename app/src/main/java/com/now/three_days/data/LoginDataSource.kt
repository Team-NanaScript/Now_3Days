package com.now.three_days.data

import android.util.Log
import com.now.three_days.data.model.LoggedInUser
import com.now.three_days.data.model.User
import com.now.three_days.service.UserViewModel
import com.now.three_days.ui.login.LoginViewModel
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource() {

//    private val userService: UserServiceImplV1
//
//    private lateinit var fireServiceStore: FireServiceStore

    private var userViewModel: UserViewModel? = null

    //    Sample Data
    val userList = arrayListOf<User>(
        User(
            "nanask", "nanow", "123456",
            "0", "sksksk"
        ),
    )

    init {
//        userService = UserServiceImplV1()
        userViewModel = UserViewModel()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        val userDataList = userViewModel?.users
        Log.d("user data from firebase", userDataList.toString())
        val list = userDataList?.value
        Log.d("user list from data", list.toString())

        val findUserId = list?.filter { it.userId == username }
        try {
            val findUser = findUserId?.filter { it.password == password }
            val user = findUser?.get(0)
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

    fun loginTest(username: String, password: String): Result<LoggedInUser> {
//        userService.select()
//        userViewModel?.getUsersFirebase()

//        val userList = userViewModel?.dataFromFireBase()
//        val userList = fireServiceStore.getList()

//        Log.d("userList firebase", userList.toString())

        val findUser = userList.filter { it.userId == username && it.password == password }
//        val findUserId = userList.filter { it.userId == username }

        try {
            // TODO: handle loggedInUser authentication
//            val findUser = findUserId.filter { it.password == password }
//                if (findUserId.get(0).password == password) findUserId
            val user = findUser.get(0)
            val loggedInUser =
                LoggedInUser(user.userId, user.nikname, user.password, user.role, user.email, true)
            return Result.Success(loggedInUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun join(username: String, password: String): Result<LoggedInUser> {
        val email = String.format("%s@naver.com", username)
        val user = User(username, username, password, "0", email)
//        userService.insert(user,"user")
        userViewModel?.insert(user, "user")
        val loggedInUser =
            LoggedInUser(user.userId, user.nikname, user.password, user.role, user.email, true)
        return Result.Success(loggedInUser)
    }

    fun logout() {
        // TODO: revoke authentication

    }
}