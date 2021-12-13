package com.now.three_days.service.impl

import android.util.Log
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.now.three_days.data.model.ChallengeVO
import com.now.three_days.service.FireService

class ChallengeServiceImplV1 : FireService<ChallengeVO, String>() {

    // 데이터 읽는 method
    override fun select(): ArrayList<ChallengeVO> {
        var list:ArrayList<ChallengeVO> = arrayListOf()
        db.collection("challenge").get().addOnSuccessListener { result->
            for(res in result){
                // 데이터 클래스에 빈 생성자 필요
                var challenge = res.toObject(ChallengeVO::class.java)
                list.add(challenge)
            }
         }
        return list
    }
}