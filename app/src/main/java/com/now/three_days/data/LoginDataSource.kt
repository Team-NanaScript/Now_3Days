package com.now.three_days.data

import com.now.three_days.data.model.LoggedInUser
import com.now.three_days.data.model.User
import com.now.three_days.service.impl.UserServiceImplV1
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(val userService:UserServiceImplV1) {

//    Sample Data
    val userList = arrayListOf<User>(
        User("nanask", "nanow", "123456",
            "0", "sksksk"),
    )

    fun login(username: String, password: String): Result<LoggedInUser> {
        val findUser = userList.filter { it.userId == username && it.password == password }
        try {
            // TODO: handle loggedInUser authentication
            val user = findUser.get(0)
            val loggedInUser =
                LoggedInUser(user.userId, user.nikname, user.password, user.role, user.email, true)
            return Result.Success(loggedInUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun join(username: String, password: String) {
//        val user:User =
//        userService.insert()
    }

    fun logout() {
        // TODO: revoke authentication

    }
}