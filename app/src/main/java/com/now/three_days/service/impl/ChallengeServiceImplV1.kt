package com.now.three_days.service.impl

import com.now.three_days.data.model.ChallengeVO
import com.now.three_days.service.FireService


class ChallengeServiceImplV1 : FireService<ChallengeVO, String>() {

    // 데이터 읽는 method
    override fun select() {

        db.collection("challenge").get().addOnSuccessListener { result ->
            var list:MutableList<ChallengeVO> = result.toObjects(ChallengeVO::class.java)

        }

    }
}