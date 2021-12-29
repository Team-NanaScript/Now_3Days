package com.now.three_days.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import com.now.three_days.data.model.RelayDTO
import com.now.three_days.service.impl.RelayServiceImplV1
import java.time.Duration
import java.time.LocalDate

class RListViewModel : ViewModel() {

//    private val _data = MutableLiveData<List<RelayVO>>()
//    val data:LiveData<List<RelayVO>> get() = _data

    private lateinit var rs: RelayServiceImplV1

    var rList: MutableLiveData<List<RelayDTO>> = MutableLiveData()

//    init {
//        _data.value = listOf()
//    }

    fun list(): LiveData<List<RelayDTO>> {
        rs = RelayServiceImplV1()
        rs.select("릴레이").addSnapshotListener(EventListener<QuerySnapshot> { snapshot, exception ->
            val data: MutableLiveData<List<RelayDTO>> = MutableLiveData()
            var list: MutableList<RelayDTO> = mutableListOf()
            if (exception != null) {
                // w 로 해야지 exception 받아짐
                Log.w("파이어 베이스 ㅋ", exception)
                data.value = null
                return@EventListener
            }
            for (doc in snapshot!!) {
                var seq = doc.id
                var obj = doc.toObject(RelayDTO::class.java)
                obj.r_seq = seq
                list.add(obj)
            }
            list.shuffle()
            rList.value = list
        })
// viewmodel 에서 데이터를 변경하고 라이브데이터가 데이터를 갱신하고, 옵저버가 받아서 화면을 갱신
        return rList
    }

    fun seq(it:List<RelayDTO>): ArrayList<String> {
        var seqList:ArrayList<String> = arrayListOf()
        var size = it.size-1
        for(i in 0..size){
            var seq = it[i].r_seq
            seqList.add(seq)
        }
        return seqList
    }

    fun date(it:List<RelayDTO>): ArrayList<RDate> {
        var dateList:ArrayList<RDate> = arrayListOf()
        val size = it.size-1
        for(i in 0..size){
            val rDate: RDate = RDate()
            val sDate = it[i].r_sDate
            val eDate = it[i].r_eDate
            rDate.r_sDate = sDate
            rDate.r_eDate = eDate
            dateList.add(rDate)
        }
        return dateList
    }

    data class RDate(
        var r_sDate:String = "",
        var r_eDate:String = ""
    )

    // 주 단위 날짜 계산
    fun weekResult(it: List<RelayDTO>) {
        val testDate1 = "2021-12-01"
        val testDate2 = "2021-12-31"
        val splitDate1 = testDate1.split("-")
        val start =
            LocalDate.of(splitDate1[0].toInt(), splitDate1[1].toInt(), splitDate1[2].toInt())
                .atStartOfDay()
        val splitDate2 = testDate2.split("-")
        val end = LocalDate.of(splitDate2[0].toInt(), splitDate2[1].toInt(), splitDate2[2].toInt())
            .atStartOfDay()

        val size = it.size - 1
        for (i in 0..size) {
            val r_sDate = it[i].r_sDate
            val r_eDate = it[i].r_eDate

            val sDateSplit = r_sDate.split("-")
            val sDate =
                LocalDate.of(sDateSplit[0].toInt(), sDateSplit[1].toInt(), sDateSplit[2].toInt())
                    .atStartOfDay()
            val eDateSplit = r_eDate.split("-")
            val eDate =
                LocalDate.of(eDateSplit[0].toInt(), eDateSplit[1].toInt(), eDateSplit[2].toInt())
                    .atStartOfDay()

            var sDateDuration = Duration.between(sDate, start).toDays()
            var eDateDuration = Duration.between(eDate, end).toDays()

            Log.d("시작날짜 계산", sDateDuration.toString())
            Log.d("종료날짜 계산", eDateDuration.toString())

//            if (sDateDuration >= 0) {
//                if(eDateDuration >= 0){
//                    Log.d("날짜 계산 ? ", it[i].toString())
//                }
//            }
        }
    }

    // 오늘 날짜 기준 계산
        fun todayResult(it: List<RelayDTO>){
            val today = LocalDate.now().atStartOfDay()

            val size = it.size-1
            for(i in 0..size){
                val r_sDate = it[i].r_sDate
                val r_eDate = it[i].r_eDate

                val sDateSplit = r_sDate.split("-")
                val sDate = LocalDate.of(sDateSplit[0].toInt(), sDateSplit[1].toInt(), sDateSplit[2].toInt()).atStartOfDay()
                val eDateSplit = r_eDate.split("-")
                val eDate = LocalDate.of(eDateSplit[0].toInt(), eDateSplit[1].toInt(), eDateSplit[2].toInt()).atStartOfDay()

                var sDateDuration = Duration.between(sDate, today).toDays()
                var eDateDuration = Duration.between(eDate, today).toDays()

                Log.d("시작날짜 계산", sDateDuration.toString())
                Log.d("종료날짜 계산", eDateDuration.toString())

                if(sDateDuration >= 0 && eDateDuration <= 0){
                    Log.d("날짜 계산 ? ", it[i].toString())
                }
            }

        }

    fun listByUserId(userId: String): LiveData<List<RelayDTO>> {
        rs = RelayServiceImplV1()
        Log.d("relay viewModel userId", userId)
        // service에는 vo 받아오는데에는 dto라서 안나오나?
        rs.select("릴레이")
            .whereEqualTo("r_userId", userId)
            .addSnapshotListener(EventListener<QuerySnapshot> { snapshot, exception ->
            val data: MutableLiveData<List<RelayDTO>> = MutableLiveData()
            var list: MutableList<RelayDTO> = mutableListOf()
            if (exception != null) {
                // w 로 해야지 exception 받아짐
                Log.w("파이어 베이스 ㅋ", exception)
                data.value = null
                return@EventListener
            }
            for (doc in snapshot!!) {
                var seq = doc.id
                var obj = doc.toObject(RelayDTO::class.java)
                obj.r_seq = seq
                list.add(obj)
            }
            rList.value = list
        })

        return rList
    }

    fun listByUserIdAndDate(userId: String, today:String): LiveData<List<RelayDTO>> {
        rs = RelayServiceImplV1()
        rs.select("릴레이")
            .whereEqualTo("r_userId", userId)
//            .whereLessThanOrEqualTo("r_eDate", today)
            .whereGreaterThanOrEqualTo("r_sDate", today)
            .addSnapshotListener(EventListener<QuerySnapshot> { snapshot, exception ->
                val data: MutableLiveData<List<RelayDTO>> = MutableLiveData()
                var list: MutableList<RelayDTO> = mutableListOf()
                if (exception != null) {
                    // w 로 해야지 exception 받아짐
                    Log.w("파이어 베이스 ㅋ", exception)
                    data.value = null
                    return@EventListener
                }
                for (doc in snapshot!!) {
                    var seq = doc.id
                    var obj = doc.toObject(RelayDTO::class.java)
                    obj.r_seq = seq
                    if(today <= obj.r_eDate)
                        list.add(obj)
                }
                rList.value = list
            })

        return rList
    }

//    fun listTest(userId: String, today:String):LiveData<List<RelayDTO>> {
//        listByUserIdAndDate(userId,today)
//    }

}
