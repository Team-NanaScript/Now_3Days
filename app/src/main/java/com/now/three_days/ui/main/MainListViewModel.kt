package com.now.three_days.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.now.three_days.data.model.AllVO
import com.now.three_days.service.FireService

class MainListViewModel : ViewModel() {

    private val _data = MutableLiveData<List<AllVO>>()
    val data = _data.value

    private lateinit var fs:FireService<AllVO, String>

    init {
        _data.value = listOf()
    }

    fun list(){
        fs.select().addOnSuccessListener { result->
            val list:List<AllVO> = result.toObjects(AllVO::class.java)
        }
    }

}