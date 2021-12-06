package com.now.three_days.data

import com.now.three_days.data.model.Challenge
import com.now.three_days.data.model.Relay

data class SectionModel(
    val category : String,
    val cList : List<Challenge>,
    val rList : List<Relay>
)
