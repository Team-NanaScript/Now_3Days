package com.now.three_days.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.now.three_days.data.model.ResolveVO
import com.now.three_days.service.impl.ResolveServiceImplV1

class ResolveViewModel : ViewModel() {

    private var _resolveList: MutableLiveData<List<ResolveVO>> = MutableLiveData()

    val resolveList: LiveData<List<ResolveVO>>
        get() = _resolveList

    private lateinit var resServie: ResolveServiceImplV1

/*
    fun copylist(): MutableLiveData<List<ChallengeDTO>> {
        cs = ChallengeServiceImplV1()
        cs.select("챌린지").addSnapshotListener(EventListener<QuerySnapshot> { snapshot, exception ->
            var data: MutableLiveData<List<ChallengeDTO>> = MutableLiveData()
            var list: MutableList<ChallengeDTO> = mutableListOf()
            if (exception != null) {
                // w 로 해야지 exception 받아짐
                Log.w("파이어 베이스 ㅋ", exception)
                data.value = null
                return@EventListener
            }
            for (doc in snapshot!!) {
                var seq = doc.id
                var obj = doc.toObject(ChallengeDTO::class.java)
                obj.c_seq = seq
                list.add(obj)
            }
            cList.value = list
        })
        return cList
    }
 */

    fun list(): LiveData<List<ResolveVO>> {
        resServie = ResolveServiceImplV1()
        resServie.select("resolve")
            .addSnapshotListener(EventListener<QuerySnapshot> { snapshot, exception ->
                var data: MutableLiveData<List<ResolveVO>> = MutableLiveData()
                var list: MutableList<ResolveVO> = mutableListOf()
                if (exception != null) {
                    Log.w("firebase error", exception)
                    data.value = null
                    return@EventListener
                }
                for (doc in snapshot!!) {
                    var item = doc.toObject(ResolveVO::class.java)
                    list.add(item)
                }
                _resolveList.value = list
            })

        return resolveList
    }

    fun listByRelaySeq(rs_r_seq: String): LiveData<List<ResolveVO>> {
        resServie = ResolveServiceImplV1()
        resServie.selectByRelaySeq(rs_r_seq)
            .addSnapshotListener(EventListener<QuerySnapshot> { snapshot, exception ->
                var data: MutableLiveData<List<ResolveVO>> = MutableLiveData()
                var list: MutableList<ResolveVO> = mutableListOf()
                if (exception != null) {
                    Log.w("firebase error", exception)
                    data.value = null
                    return@EventListener
                }
                for (doc in snapshot!!) {
                    var item = doc.toObject(ResolveVO::class.java)
                    list.add(item)
                }
                _resolveList.value = list
            })
        return resolveList
    }
}