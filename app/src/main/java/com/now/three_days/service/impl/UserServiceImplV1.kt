package com.now.three_days.service.impl


import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.now.three_days.data.model.User
import com.now.three_days.service.FireService
import com.now.three_days.service.FireServiceStore

class UserServiceImplV1 : FireService<User, String>() {


    private fun selectAll() {
        var list: ArrayList<User> = arrayListOf()
        db.collection("user").get().addOnSuccessListener { result ->
            for (res in result) {

    // 데이터 읽는 method
    override fun select(){
        var list:ArrayList<User> = arrayListOf()
        db.collection("user").get().addOnSuccessListener { result->
            for(res in result){

                // 데이터 클래스에 빈 생성자 필요
                var user = res.toObject(User::class.java)
                list.add(user)
                Log.d("user list", list.toString())
            }

         }

    }


}