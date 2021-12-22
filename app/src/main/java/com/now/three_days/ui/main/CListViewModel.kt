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

    fun list(): MutableLiveData<List<ChallengeDTO>> {

        cs = ChallengeServiceImplV1()
        cs.select("챌린지").addSnapshotListener(EventListener<QuerySnapshot>{ snapshot, exception ->
            var data:MutableLiveData<List<ChallengeDTO>> = MutableLiveData()
            var list:MutableList<ChallengeDTO> = mutableListOf()
            if(exception != null){
                // w 로 해야지 exception 받아짐
                Log.w("파이어 베이스 ㅋ", exception)
                data.value = null
                return@EventListener
            }
            for(doc in snapshot!!){
                var seq = doc.id
                var obj = doc.toObject(ChallengeDTO::class.java)
                obj.c_seq = seq
                list.add(obj)
            }
            cList.value = list

        })

        return cList
    }

}