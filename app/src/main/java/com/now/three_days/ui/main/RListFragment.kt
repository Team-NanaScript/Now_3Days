package com.now.three_days.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.GridLayoutManager
import com.now.three_days.R
import com.now.three_days.adapter.RListAdapter
import com.now.three_days.data.model.RelayVO
import com.now.three_days.databinding.ItemRListBinding
import com.now.three_days.databinding.RListFragmentBinding

class RListFragment : Fragment() {

    lateinit var rListAdapter1: RListAdapter
    lateinit var rListAdapter2: RListAdapter

    private val rList1 = ArrayList<RelayVO>()
    private val rList2 = ArrayList<RelayVO>()

    private var _binding: RListFragmentBinding?  = null
//    private var _binding1: ItemRListBinding? = null
    val binding get() = _binding!!

    companion object {
        fun newInstance() = RListFragment()
    }

    private lateinit var viewModel: RListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = RListFragmentBinding.inflate(inflater,container,false)
//        _binding = ItemRListBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RListViewModel::class.java)

//        viewModel.list()
        viewModel.data.observe(viewLifecycleOwner,Observer {
            Log.d("ViewModel {}", "$it")
            binding.allList.adapter = RListAdapter(it)

//            if(it.get(0) ) {
                binding.bestList.adapter = RListAdapter(it)
//            }

            // bestList에 조건을 담아서 item 개수 조정하기


        })
        rListAdapter2 = RListAdapter(rList2)
        rListAdapter1 = RListAdapter(rList1)

        rList1.apply {
            add(RelayVO("1","나나","1L 마시기", "2021-11-06", "2021-11-09", "1L 마시기"))
            add(RelayVO("1L 마시기", "2021-11-06~2021-11-09"))
        }
//
        rList2.apply {
            add(RelayVO("은결이한테 질척거리기", "2021-11-06~2021-11-09"))
            add(RelayVO("영진이 놀리기", "2021-11-06~2021-11-30"))
            add(RelayVO("은빈언니한테 물어보기", "2021-11-06~2021-11-09"))
            add(RelayVO("영진이 놀리기", "2021-11-06~2021-11-30"))
            add(RelayVO("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
            add(RelayVO("영진이 놀리기", "2021-11-06~2021-11-30"))
            add(RelayVO("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
            add(RelayVO("영진이 놀리기", "2021-11-06~2021-11-30"))
        }

        binding.allList.adapter = rListAdapter2

        binding.allList.layoutManager = GridLayoutManager(context, 2)
        binding.bestList.layoutManager = GridLayoutManager(context, 2)

    }


}