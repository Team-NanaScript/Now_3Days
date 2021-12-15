package com.now.three_days.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.now.three_days.R
import com.now.three_days.adapter.RListAdapter
import com.now.three_days.data.model.CListData
import com.now.three_days.data.model.RListData
import com.now.three_days.databinding.FragmentRListBinding


class RListFragment : Fragment() {

    lateinit var rListAdapter1 : RListAdapter
    lateinit var rListAdapter2 : RListAdapter

    val rList1 = ArrayList<RListData>()
    val rList2 = ArrayList<RListData>()

    private lateinit var viewModel: MainListViewModel

    private var _binding: FragmentRListBinding? = null
    private val binding get() = _binding!!



    companion object {
        fun newInstance() = RListFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        rListAdapter1 = RListAdapter(rList1)
//        rListAdapter2 = RListAdapter(rList2)
        rListAdapter1 = RListAdapter()
        rListAdapter2 = RListAdapter()

        rList1.apply {
            add(RListData("1L 마시기", "2021-11-06~2021-11-09"))
            add(RListData("1L 마시기", "2021-11-06~2021-11-09"))
        }

        rList2.apply {
            add(RListData("은결이한테 질척거리기", "2021-11-06~2021-11-09"))
            add(RListData("영진이 놀리기", "2021-11-06~2021-11-30"))
            add(RListData("은빈언니한테 물어보기", "2021-11-06~2021-11-09"))
            add(RListData("영진이 놀리기", "2021-11-06~2021-11-30"))
            add(RListData("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
            add(RListData("영진이 놀리기", "2021-11-06~2021-11-30"))
            add(RListData("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
            add(RListData("영진이 놀리기", "2021-11-06~2021-11-30"))
        }

        Log.d("RListFragment",rList1.toArray().toString())

        rListAdapter1.rList = rList1
        rListAdapter2.rList = rList2

        binding.allList.adapter = rListAdapter2
        binding.bestList.adapter = rListAdapter1
        binding.allList.layoutManager = GridLayoutManager(context, 2)
        binding.bestList.layoutManager = GridLayoutManager(context, 2)
    }
}