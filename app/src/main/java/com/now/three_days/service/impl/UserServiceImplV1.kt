package com.now.three_days.service.impl

<<<<<<< HEAD
import android.util.Log
=======
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
>>>>>>> adce7633b71839a90994e00b73ed104ce6c8b23d
import com.now.three_days.data.model.User
import com.now.three_days.service.FireService
import com.now.three_days.service.FireServiceStore

class UserServiceImplV1 : FireService<User, String>() {

<<<<<<< HEAD
    //    private lateinit var loginViewModel:LoginViewModel
//    private lateinit var fireServiceStore: FireServiceStore

    override fun select(): ArrayList<User> {
        var userList: ArrayList<User> = arrayListOf()
        this.selectAll()
        return userList
//        return fireServiceStore.getList()
    }

    private fun selectAll() {
        var list: ArrayList<User> = arrayListOf()
        db.collection("user").get().addOnSuccessListener { result ->
            for (res in result) {
=======
    // 데이터 읽는 method
    override fun select(){
        var list:ArrayList<User> = arrayListOf()
        db.collection("user").get().addOnSuccessListener { result->
            for(res in result){
>>>>>>> adce7633b71839a90994e00b73ed104ce6c8b23d
                // 데이터 클래스에 빈 생성자 필요
                var user = res.toObject(User::class.java)
                list.add(user)
                Log.d("user list", list.toString())
            }
<<<<<<< HEAD
//            fireServiceStore.setList(list)
        }

=======
         }
>>>>>>> adce7633b71839a90994e00b73ed104ce6c8b23d
    }


}