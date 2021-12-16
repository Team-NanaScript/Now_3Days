package com.now.three_days.service.impl

<<<<<<< HEAD
<<<<<<< HEAD
import android.util.Log
=======
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
>>>>>>> adce7633b71839a90994e00b73ed104ce6c8b23d
=======

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
>>>>>>> 79ff2d91575b1851d726298bbc1dc4458017bc31
import com.now.three_days.data.model.User
import com.now.three_days.service.FireService
import com.now.three_days.service.FireServiceStore

class UserServiceImplV1 : FireService<User, String>() {

<<<<<<< HEAD
<<<<<<< HEAD
    //    private lateinit var loginViewModel:LoginViewModel
//    private lateinit var fireServiceStore: FireServiceStore

    override fun select(): ArrayList<User> {
        var userList: ArrayList<User> = arrayListOf()
        this.selectAll()
        return userList
//        return fireServiceStore.getList()
    }
=======
>>>>>>> 79ff2d91575b1851d726298bbc1dc4458017bc31

    private fun selectAll() {
        var list: ArrayList<User> = arrayListOf()
        db.collection("user").get().addOnSuccessListener { result ->
            for (res in result) {
<<<<<<< HEAD
=======
=======

>>>>>>> 79ff2d91575b1851d726298bbc1dc4458017bc31
    // 데이터 읽는 method
    override fun select(){
        var list:ArrayList<User> = arrayListOf()
        db.collection("user").get().addOnSuccessListener { result->
            for(res in result){
<<<<<<< HEAD
>>>>>>> adce7633b71839a90994e00b73ed104ce6c8b23d
=======

>>>>>>> 79ff2d91575b1851d726298bbc1dc4458017bc31
                // 데이터 클래스에 빈 생성자 필요
                var user = res.toObject(User::class.java)
                list.add(user)
                Log.d("user list", list.toString())
            }
<<<<<<< HEAD
<<<<<<< HEAD
//            fireServiceStore.setList(list)
        }

=======
         }
>>>>>>> adce7633b71839a90994e00b73ed104ce6c8b23d
=======

         }

>>>>>>> 79ff2d91575b1851d726298bbc1dc4458017bc31
    }


}