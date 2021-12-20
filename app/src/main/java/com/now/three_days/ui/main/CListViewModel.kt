package com.now.three_days.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.now.three_days.data.model.ChallengeVO
import com.now.three_days.service.impl.ChallengeServiceImplV1

class CListViewModel : ViewModel() {

    private val _data = MutableLiveData<List<ChallengeVO>>()
    val data:LiveData<List<ChallengeVO>> get() = _data
    private lateinit var cs:ChallengeServiceImplV1

    init {
        _data.value = listOf()
    }

    fun list(){

        cs = ChallengeServiceImplV1()
        cs.select("챌린지").addOnSuccessListener { result->
            val list = result.toObjects(ChallengeVO::class.java)
            _data.value = _data.value?.plus(list)
        }

    }

}