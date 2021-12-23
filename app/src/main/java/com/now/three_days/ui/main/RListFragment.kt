package com.now.three_days.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.now.three_days.adapter.RListAdapter
import com.now.three_days.data.model.RelayDTO
import com.now.three_days.databinding.RListFragmentBinding

class RListFragment : Fragment() {

    lateinit var rListAdapter1: RListAdapter
    lateinit var rListAdapter2: RListAdapter

    private val rList1 = ArrayList<RelayDTO>()
    private val rList2 = ArrayList<RelayDTO>()

    private var _binding: RListFragmentBinding? = null

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

        _binding = RListFragmentBinding.inflate(inflater, container, false)
//        _binding = ItemRListBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(RListViewModel::class.java)

//        viewModel.list()
//        viewModel.list().observe(viewLifecycleOwner, Observer {
        viewModel.list().observe(viewLifecycleOwner, Observer {
            Log.d("ViewModel {}", "$it")
//            고쳐주세요
//            binding.allList.adapter = RListAdapter(it)

            // cListAdapter1 에 it 을 담아주고
            rListAdapter1 = RListAdapter(it)
            // cListAdapter1 을 allList를 보여주는 adapter로 설정
            binding.allList.adapter = rListAdapter1

//            if(it.get(0) ) {
//                binding.bestList.adapter = RListAdapter(it)
//            }

            // bestList에 조건을 담아서 item 개수 조정하기
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rListAdapter2 = RListAdapter(rList1)
//        rListAdapter1 = RListAdapter(rList1)

        rList1.apply {
            add(RelayDTO("1", "나나", "1L 마시기", "2021-11-06", "2021-11-09", "1L 마시기"))
            add(RelayDTO("2", "ㅇㅇ", "1L 마시기", "2021-11-06", "2021-11-09", "1L 마시기"))
        }
//
//        rList2.apply {
//            add(RelayDTO("은결이한테 질척거리기", "2021-11-06~2021-11-09"))
//            add(RelayDTO("영진이 놀리기", "2021-11-06~2021-11-30"))
//            add(RelayDTO("은빈언니한테 물어보기", "2021-11-06~2021-11-09"))
//            add(RelayDTO("영진이 놀리기", "2021-11-06~2021-11-30"))
//            add(RelayDTO("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
//            add(RelayDTO("영진이 놀리기", "2021-11-06~2021-11-30"))
//            add(RelayDTO("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
//            add(RelayDTO("영진이 놀리기", "2021-11-06~2021-11-30"))
//        }

//        binding.allList.adapter = rListAdapter2
        binding.bestList.adapter = rListAdapter2
        binding.allList.layoutManager = GridLayoutManager(context, 2)
        binding.bestList.layoutManager = GridLayoutManager(context, 2)

    }


}