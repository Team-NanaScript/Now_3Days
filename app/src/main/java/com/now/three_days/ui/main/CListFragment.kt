package com.now.three_days.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.now.three_days.R
import com.now.three_days.adapter.CListAdapter
import com.now.three_days.data.model.CListData
import com.now.three_days.data.model.RListData
import com.now.three_days.databinding.CListFragmentBinding

class CListFragment : Fragment() {

    lateinit var cListAdapter1 : CListAdapter
    lateinit var cListAdapter2 : CListAdapter

    private val cList1 = ArrayList<CListData>()
    private val cList2 = ArrayList<CListData>()

    private var _binding : CListFragmentBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = CListFragment()
    }

    private lateinit var viewModel: CListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = CListFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CListViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cListAdapter1 = CListAdapter(cList1)
        cListAdapter2 = CListAdapter(cList2)

        cList1.apply {
            add(CListData("1L 마시기", "2021-11-06~2021-11-09"))
            add(CListData("1L 마시기", "2021-11-06~2021-11-09"))
        }

        cList2.apply {
            add(CListData("은결이한테 질척거리기", "2021-11-06~2021-11-09"))
            add(CListData("영진이 놀리기", "2021-11-06~2021-11-30"))
            add(CListData("은빈언니한테 물어보기", "2021-11-06~2021-11-09"))
            add(CListData("영진이 놀리기", "2021-11-06~2021-11-30"))
            add(CListData("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
            add(CListData("영진이 놀리기", "2021-11-06~2021-11-30"))
            add(CListData("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
            add(CListData("영진이 놀리기", "2021-11-06~2021-11-30"))
        }

        binding.bestList.adapter = cListAdapter1
        binding.allList.adapter = cListAdapter2
        binding.bestList.layoutManager = GridLayoutManager(context,2)
        binding.allList.layoutManager = GridLayoutManager(context,2)

    }
}