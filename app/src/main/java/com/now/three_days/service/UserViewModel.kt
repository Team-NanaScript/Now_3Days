package com.now.three_days.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.now.three_days.data.model.User

class UserViewModel : ViewModel() {

    var users: MutableLiveData<List<User>> = MutableLiveData()

    protected val db = Firebase.firestore

    fun getUsers(): LiveData<List<User>> {
//        if (users.value == null){
        var list: ArrayList<User> = arrayListOf()
        db.collection("user").get().addOnSuccessListener { result ->
            for (res in result) {
                // 데이터 클래스에 빈 생성자 필요
                var user = res.toObject(User::class.java)
                list.add(user)
                Log.d("user list", list.toString())
            }
//            fireServiceStore.setList(list)
            users.postValue(list)
        }
//        }

        return users
    }
}