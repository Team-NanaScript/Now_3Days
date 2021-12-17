package com.now.three_days.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.now.three_days.adapter.RListAdapter
import com.now.three_days.adapter.CListAdapter
import com.now.three_days.adapter.ViewPagerAdapter
import com.now.three_days.data.model.RListData
import com.now.three_days.data.model.CListData
import com.now.three_days.databinding.MainListFragmentBinding

class MainListFragment : Fragment() {


    private var _binding: MainListFragmentBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = MainListFragment()
    }

    private lateinit var viewModel: MainListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MainListFragmentBinding.inflate(inflater, container, false)

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        viewPager.adapter = ViewPagerAdapter(this)
        val tabTitle = arrayListOf<String>("Challenge", "Relay")
        TabLayoutMediator(tabLayout,viewPager){tab, position ->
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