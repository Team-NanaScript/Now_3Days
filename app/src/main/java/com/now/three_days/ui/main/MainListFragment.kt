package com.now.three_days.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.now.three_days.adapter.RListAdapter
import com.now.three_days.adapter.CListAdapter
import com.now.three_days.data.model.RListData
import com.now.three_days.data.model.CListData
import com.now.three_days.databinding.MainListFragmentBinding

class MainListFragment : Fragment() {

    lateinit var allListAdapter: RListAdapter
    lateinit var bestListAdapter: CListAdapter

    val cList = ArrayList<CListData>()
    val rList = ArrayList<RListData>()

//    val bdata = mutableListOf<CListData>()
//    val adata = mutableListOf<RListData>()

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

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bestListAdapter = CListAdapter(cList)

//        cList.add(CListData("1L 마시기","2021-11-06~2021-11-09"))
//        rList.add(RListData("1KM 달리기","2021-11-06~2021-11-30"))

//         가상데이터 만들어주기

        cList.apply {
            add(CListData("1L 마시기", "2021-11-06~2021-11-09"))
            add(CListData("1KM 달리기", "2021-11-06~2021-11-30"))
        }
        cList.apply {
            add(CListData("은결이한테 질척거리기", "2021-11-06~2021-11-09"))
            add(CListData("영진이 놀리기", "2021-11-06~2021-11-30"))
            add(CListData("은빈언니한테 물어보기", "2021-11-06~2021-11-09"))
            add(CListData("영진이 놀리기", "2021-11-06~2021-11-30"))
            add(CListData("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
            add(CListData("영진이 놀리기", "2021-11-06~2021-11-30"))
        }



//        binding.allList.adapter = allListAdapter
//        binding.allList.layoutManager = GridLayoutManager(context, 2)
//        binding.bestList.adapter = allListAdapter
//        binding.bestList.layoutManager = GridLayoutManager(context, 2)

    }

}