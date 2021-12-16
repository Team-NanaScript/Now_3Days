package com.now.three_days.service

import androidx.lifecycle.ViewModel
import com.now.three_days.data.model.User

class FireServiceStore : ViewModel() {

    private var userList: ArrayList<User> = ArrayList()

    fun setList(list: ArrayList<User>) {
        userList = list
    }

    fun getList(): ArrayList<User> {
        return userList
    }
}