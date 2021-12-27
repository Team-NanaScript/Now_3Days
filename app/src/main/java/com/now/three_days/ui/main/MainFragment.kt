package com.now.three_days.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.now.three_days.MainActivity
import com.now.three_days.R
import com.now.three_days.adapter.CalendarAdapter
import com.now.three_days.adapter.ListAdapter
import com.now.three_days.adapter.ViewPagerMainAdapter
import com.now.three_days.data.model.CalendarVO
import com.now.three_days.databinding.ListFragmentBinding
import com.now.three_days.ui.AuthFragmentParent

class MainFragment : AuthFragmentParent() {

    lateinit var listAdapter: ListAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    // ====== list ======
    private lateinit var viewModel: MainViewModel
    private var _binding: ListFragmentBinding? = null
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
        _binding = ListFragmentBinding.inflate(inflater, container, false)

        val mainAct = activity as MainActivity
        mainAct?.setBottomNav(true)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        binding.viewPager.setBackgroundResource(R.color.main)

        viewPager.adapter = ViewPagerMainAdapter(this)
        val tabTitle = arrayListOf<String>("Challenge", "Relay")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
        clistView = ViewModelProvider(this).get(CListViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}