package com.now.three_days.service

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

abstract class FireService<VO, PK> {

    protected val db = Firebase.firestore

    fun insert(vo: VO, category: String) {

        db.collection(category).add(vo!!)

    }

    fun select(category: String): CollectionReference {

//        db.collection("릴레이").document(시퀀스).collection("댓글").add()

        return db.collection(category)
    }

    fun insertComment(category: String, seq:String, comment:String, vo:VO){

        db.collection(category).document(seq).collection(comment).add(vo!!)

    }

    fun selectComment(category: String, seq:String, comment:String): CollectionReference {

        return db.collection(category).document(seq).collection(comment)
    }

}