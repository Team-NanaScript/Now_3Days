package com.now.three_days.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String?,
    val nikname: String?,
    val password: String?,
    val role: String?,
    val email: String?,
    val isLogin: Boolean
)