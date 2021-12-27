package com.now.three_days.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.now.three_days.adapter.ViewPagerMainAdapter
import com.now.three_days.databinding.ListFragmentBinding


class MainFragment : Fragment() {

    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ListFragmentBinding.inflate(inflater, container, false)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        viewPager.adapter = ViewPagerMainAdapter(this)
        val tabTitle = arrayListOf<String>("Challenge", "Relay")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}