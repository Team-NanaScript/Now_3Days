package com.now.three_days.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.now.three_days.data.model.RelayVO
import com.now.three_days.service.impl.RelayServiceImplV1

class RListViewModel : ViewModel() {

    private val _data = MutableLiveData<List<RelayVO>>()
    val data:LiveData<List<RelayVO>> get() = _data

    private lateinit var rs:RelayServiceImplV1

    init {
        _data.value = listOf()
    }

    fun list(){
        rs = RelayServiceImplV1()
        rs.select("릴레이").addOnSuccessListener { result->
            val list = result.toObjects(RelayVO::class.java)
            _data.value = _data.value?.plus(list)
        }
    }

}