package com.now.three_days.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObjects
import com.now.three_days.data.model.ChallengeDTO
import com.now.three_days.data.model.ChallengeVO
import com.now.three_days.service.impl.ChallengeServiceImplV1

class CListViewModel : ViewModel() {

//    private var _data = MutableLiveData<List<ChallengeDTO>>()
//    val data:LiveData<List<ChallengeDTO>> get() = _data

    var cList : MutableLiveData<List<ChallengeDTO>> = MutableLiveData()
    private lateinit var cs:ChallengeServiceImplV1

//    init {
//        _data.value = listOf()
//    }

    fun list(){

        cs = ChallengeServiceImplV1()
        cs.select("챌린지").addSnapshotListener(EventListener<QuerySnapshot>{ snapshot, exception ->
            val data:MutableLiveData<List<ChallengeDTO>> = MutableLiveData()
            if(exception != null){
                // w 로 해야지 exception 받아짐
                Log.w("파이어 베이스 ㅋ", exception)
                data.value = null
                return@EventListener
            }
            cList.value = snapshot?.toObjects(ChallengeDTO::class.java)
            Log.d("리스트는 ?? ", cList.toString())

        })

    }

}