package com.now.three_days.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.now.three_days.data.model.CheerVO
import com.now.three_days.data.model.ResolveVO
import com.now.three_days.service.impl.CheerServiceImplV1
import com.now.three_days.service.impl.ResolveServiceImplV1

class CheerViewModel : ViewModel() {

    private var _cheerList: MutableLiveData<List<CheerVO>> = MutableLiveData()

    val cheerList: LiveData<List<CheerVO>>
        get() = _cheerList

    private lateinit var cheerServie: CheerServiceImplV1

    fun list(): LiveData<List<CheerVO>> {
        cheerServie = CheerServiceImplV1()
        cheerServie.select("cheer")
            .addSnapshotListener(EventListener<QuerySnapshot> { snapshot, exception ->
                var data: MutableLiveData<List<CheerVO>> = MutableLiveData()
                var list: MutableList<CheerVO> = mutableListOf()
                if (exception != null) {
                    Log.w("firebase error", exception)
                    data.value = null
                    return@EventListener
                }
                for (doc in snapshot!!) {
                    var item = doc.toObject(CheerVO::class.java)
                    list.add(item)
                }
                _cheerList.value = list
            })

        return cheerList
    }

    fun listByChallSeq(ch_c_seq: String): LiveData<List<CheerVO>> {
        cheerServie = CheerServiceImplV1()
        cheerServie.selectComment("챌린지", ch_c_seq, "댓글")
            .addSnapshotListener(EventListener<QuerySnapshot> { snapshot, exception ->
                var data: MutableLiveData<List<CheerVO>> = MutableLiveData()
                var list: MutableList<CheerVO> = mutableListOf()
                if (exception != null) {
                    Log.w("firebase error", exception)
                    data.value = null
                    return@EventListener
                }
                for (doc in snapshot!!) {
                    var item = doc.toObject(CheerVO::class.java)
                    list.add(item)
                }
                _cheerList.value = list
            })
        return cheerList
    }
}