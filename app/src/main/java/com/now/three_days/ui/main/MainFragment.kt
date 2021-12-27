package com.now.three_days.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.now.three_days.MainActivity
import com.now.three_days.R
import com.now.three_days.adapter.CalendarAdapter
import com.now.three_days.adapter.ListAdapter
import com.now.three_days.data.model.CalendarVO
import com.now.three_days.data.model.List_Data
import com.now.three_days.databinding.MainFragmentBinding
import com.now.three_days.ui.AuthFragmentParent
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : AuthFragmentParent() {

    lateinit var listAdapter: ListAdapter
    val data = mutableListOf<List_Data>()

    companion object {
        fun newInstance() = MainFragment()
    }

    // ====== list ======
    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    // ====== calendar ======
    private lateinit var clistView: CListViewModel
    lateinit var calendarAdapter: CalendarAdapter
    private var cList = ArrayList<CalendarVO>()
//    private var cList = MutableLiveData<CalendarVO>() yo ga code da

    // mainFragment에서 만들어둔 view를 보여주도록 연결하기
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        val mainAct = activity as MainActivity
        mainAct?.setBottomNav(true)

        clistView = ViewModelProvider(this).get(CListViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ====== list ======
        listAdapter = ListAdapter(this)

        data.apply {
            add(List_Data("", "1L 마시기", "1L 마시기"))
            add(List_Data("", "1km 달리기", "1km 달리기"))
            add(List_Data("", "1시간 취미생활 즐기기", "1시간 취미생활 즐기기"))
            add(List_Data("", "은결이 칭찬하기", "은결이 칭찬하기"))
            add(List_Data("", "영진이 괴롭히기", "영진이 괴롭히기"))

            // 만들어둔 Adapter에 data 연결하기
            listAdapter.data = data
        }

        // 리사이클러뷰 adapter를 만들어놓은 ListAdapter로 사용하겠다
        binding.rcList.adapter = listAdapter

        // ====== 캘린더 ======
        var week_day: Array<String> = resources.getStringArray(R.array.calendar_day)

        calendarAdapter = CalendarAdapter(cList)

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
//            Log.d("오늘은 몇요일..?", week_day[i])
            cList.apply {
                add(CalendarVO(preSunday.plusDays(i.toLong()).format(dateFormat), week_day[i]))
            }
//            Log.d("날짜 기준", preSunday.plusDays(i.toLong()).format(dateFormat))
        }

        binding.calendarRecyclerview.adapter = calendarAdapter
        binding.calendarRecyclerview.layoutManager = GridLayoutManager(context, 7)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}