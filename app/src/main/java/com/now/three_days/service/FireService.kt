package com.now.three_days.service

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FireService {

    val firebase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val dbRef: DatabaseReference = firebase.reference


}