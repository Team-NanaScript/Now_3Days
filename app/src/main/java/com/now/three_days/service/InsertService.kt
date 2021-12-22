package com.now.three_days.service

import android.content.Context
import com.now.three_days.MainActivity
import com.now.three_days.databinding.InsertFragmentBinding

interface InsertService {

    fun onClick(binding: InsertFragmentBinding, mainActivity: MainActivity, context: Context)
    fun insert(select_text:String, title:String, content:String, sDate:String, eDate:String, userId:String)

}