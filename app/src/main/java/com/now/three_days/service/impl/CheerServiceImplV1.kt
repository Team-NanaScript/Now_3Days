package com.now.three_days.service.impl

import android.util.Log
import com.google.firebase.firestore.Query
import com.now.three_days.data.model.CheerVO
import com.now.three_days.data.model.ResolveVO
import com.now.three_days.service.FireService

class CheerServiceImplV1 : FireService<CheerVO, String>() {

    fun selectByChallSeq(ch_c_seq: String): Query {
        return select("cheer").whereEqualTo("ch_c_seq", ch_c_seq)
    }
}