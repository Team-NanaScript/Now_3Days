package com.now.three_days.service

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


abstract class FireService<VO, PK> {

    private val db = Firebase.firestore

    fun insert(vo:VO, category:String){

        db.collection(category).add(vo!!)

    }




}