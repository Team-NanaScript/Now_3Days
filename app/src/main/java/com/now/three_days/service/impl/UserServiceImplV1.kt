package com.now.three_days.service.impl

import com.now.three_days.data.model.User

class UserServiceImplV1 {

//    private lateinit var loginViewModel: LoginViewModel
//    private lateinit var fireServiceStore: FireServiceStore

    fun select() {
        var userList: ArrayList<User> = arrayListOf()
        this.selectAll()
//        return userList
//        return fireServiceStore.getList()
    }

    private fun selectAll() {
        var list: ArrayList<User> = arrayListOf()
//        db.collection("user").get().addOnSuccessListener { result ->
//            for (res in result) {

//                // 데이터 클래스에 빈 생성자 필요
//                var user = res.toObject(User::class.java)
//                list.add(user)
//                Log.d("user list", list.toString())
//            }
//            fireServiceStore.setList(list)
    }

}

//}