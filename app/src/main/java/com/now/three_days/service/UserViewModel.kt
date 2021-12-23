package com.now.three_days.service

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.now.three_days.data.model.User

class UserViewModel : ViewModel() {

    //    private val users: MutableLiveData<List<User>> = MutableLiveData()
    private val _users = MutableLiveData<List<User>>()

    val users: LiveData<List<User>>
        get() = _users

    protected val db = Firebase.firestore

//    초기값 설정
//    요 UserViewModel이 생성되면 자동으로 초기화 하는 코드임
    init {
        _users.value = listOf()
        getUsersFirebase()
    }

    /*
        fun getUsersView(): LiveData<List<User>> {
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
    //            users.postValue(list)
            }
    //        }

            return userList
        }
    */
    fun getUsersFirebase() {
        db.collection("user").get().addOnSuccessListener { result ->
            for (res in result) {
                // 데이터 클래스에 빈 생성자 필요
                var user = res.toObject(User::class.java)

                _users.value = _users.value?.plus(user)
//                users.add(user)
//                users.postValue(listOf(user))
                Log.d("get user firebase", user.toString())
            }
        }
    }

    fun dataFromFireBase(): LiveData<List<User>> {
        return users
    }

    fun insert(user: User, category: String) {
        db.collection(category).add(user!!)
    }
}