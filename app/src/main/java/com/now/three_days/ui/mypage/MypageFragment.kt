package com.now.three_days.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.now.three_days.adapter.ViewPagerMainAdapter
import com.now.three_days.data.viewmodel.CListViewModel
import com.now.three_days.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    private lateinit var chListView: CListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chListView = ViewModelProvider(this).get(CListViewModel::class.java)

        _binding = FragmentMypageBinding.inflate(inflater, container, false)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        viewPager.adapter = ViewPagerMainAdapter(this)
        val tabTitle = arrayListOf<String>("Challenge","Relay")
        TabLayoutMediator(tabLayout, viewPager) {
            tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}