package com.now.three_days.service.impl

import android.util.Log
import com.google.firebase.firestore.Query
import com.now.three_days.data.model.ResolveVO
import com.now.three_days.service.FireService

class ResolveServiceImplV1 : FireService<ResolveVO, String>() {

    /*
    // Create a reference to the cities collection
    val citiesRef = db.collection("cities")

    // Create a query against the collection.
    val query = citiesRef.whereEqualTo("state", "CA")
     */
    fun selectByRelaySeq(rs_r_seq: String): Query {
        return select("resolve").whereEqualTo("rs_r_seq", rs_r_seq)
    }
}