package com.now.three_days.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.now.three_days.MainActivity
import com.now.three_days.R
import com.now.three_days.adapter.CListAdapter
import com.now.three_days.adapter.CalendarAdapter
import com.now.three_days.adapter.ListAdapter
import com.now.three_days.adapter.RListAdapter
import com.now.three_days.data.model.CalendarVO
import com.now.three_days.data.model.ChallengeDTO
import com.now.three_days.data.model.List_Data
import com.now.three_days.data.model.RelayDTO
import com.now.three_days.databinding.MainCFragmentBinding
import com.now.three_days.databinding.MainRFragmentBinding
import com.now.three_days.ui.AuthFragmentParent
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*
import kotlin.collections.ArrayList

class MainRFragment : AuthFragmentParent() {

    private lateinit var rListAdapter: RListAdapter

    companion object {
        fun newInstance() = MainRFragment()
    }

    // ====== list ======
    private lateinit var viewModel: RListViewModel
    private val mainRList = ArrayList<RelayDTO>()
    private var _binding: MainRFragmentBinding? = null
    private val binding get() = _binding!!

    // ====== calendar ======
//    private lateinit var clistView: CListViewModel
    lateinit var calendarAdapter: CalendarAdapter
    private var cList = ArrayList<CalendarVO>()


    // mainFragment에서 만들어둔 view를 보여주도록 연결하기
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainRFragmentBinding.inflate(inflater, container, false)

        val mainAct = activity as MainActivity
        mainAct?.setBottomNav(true)

//        clistView = ViewModelProvider(this).get(CListViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ====== list ======

//        rListAdapter = RListAdapter(mainRList)

//        data.apply {
//            add(List_Data("", "1L 마시기", "1L 마시기"))
//            add(List_Data("", "1km 달리기", "1km 달리기"))
//            add(List_Data("", "1시간 취미생활 즐기기", "1시간 취미생활 즐기기"))
//            add(List_Data("", "은결이 칭찬하기", "은결이 칭찬하기"))
//            add(List_Data("", "영진이 괴롭히기", "영진이 괴롭히기"))
//
//            // 만들어둔 Adapter에 data 연결하기
//            listAdapter.data = data
//        }

        // 리사이클러뷰 adapter를 만들어놓은 ListAdapter로 사용하겠다
//        binding.rcList.adapter = rListAdapter

        // ====== 캘린더 ======
        var week_day: Array<String> = resources.getStringArray(R.array.calendar_day)

        cList.removeAll(cList)
        calendarAdapter = CalendarAdapter(cList)
        cList.apply {
            val dateFormat =
                DateTimeFormatter.ofPattern("dd").withLocale(Locale.forLanguageTag("ko"))
            val monthFormat =
                DateTimeFormatter.ofPattern("yyyy년 MM월").withLocale(Locale.forLanguageTag("ko"))

            val localDate = LocalDateTime.now().format(monthFormat)
            binding.dayTextView.text = localDate

            var preSunday: LocalDateTime = LocalDateTime.now().with(
                TemporalAdjusters.previous(
                    DayOfWeek.SUNDAY
                )
            )

            for (i in 0..6) {
                cList.apply {
                    add(CalendarVO(preSunday.plusDays(i.toLong()).format(dateFormat), week_day[i]))
                }
            }
        }

        binding.calendarRRecyclerview.adapter = calendarAdapter
        binding.calendarRRecyclerview.layoutManager  = GridLayoutManager(context,7)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RListViewModel::class.java)

        viewModel.list().observe(viewLifecycleOwner, Observer{
            rListAdapter = RListAdapter(it as ArrayList<RelayDTO>)
            binding.rList.adapter = rListAdapter
            Log.d("mainRList","$it")
        })
        // TODO: Use the ViewModel
    }

}