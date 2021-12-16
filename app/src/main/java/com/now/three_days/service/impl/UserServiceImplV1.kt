package com.now.three_days.service.impl

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.now.three_days.data.model.User
import com.now.three_days.service.FireService

class UserServiceImplV1 : FireService<User, String>() {

    // 데이터 읽는 method
    override fun select(){
        var list:ArrayList<User> = arrayListOf()
        db.collection("user").get().addOnSuccessListener { result->
            for(res in result){
                // 데이터 클래스에 빈 생성자 필요
                var user = res.toObject(User::class.java)
                list.add(user)
            }
         }
    }

}