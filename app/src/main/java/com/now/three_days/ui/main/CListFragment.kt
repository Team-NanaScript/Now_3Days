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
import com.now.three_days.adapter.CListAdapter
import com.now.three_days.data.model.ChallengeDTO
import com.now.three_days.databinding.CListFragmentBinding

class CListFragment : Fragment() {

    lateinit var cListAdapter1: CListAdapter
    lateinit var cListAdapter2: CListAdapter

    private val cList1 = ArrayList<ChallengeDTO>()
    private val cList2 = ArrayList<ChallengeDTO>()

    private var _binding: CListFragmentBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = CListFragment()
    }

    private lateinit var viewModel: CListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = CListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CListViewModel::class.java)

        viewModel.list().observe(viewLifecycleOwner, Observer {
            Log.d("ViewModel {}", "$it")

            cListAdapter1 = CListAdapter(it)
            binding.allList.adapter = cListAdapter1

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cListAdapter2 = CListAdapter(cList1)
//        cListAdapter2 = CListAdapter(cList2)

//        val dataObserver: Observer<ArrayList<ChallengeDTO>> = Observer {
//
//        }

//        cList1.apply {
//        }

        cList1.apply {
            add(ChallengeDTO("1", "나나", "1L 마시기", "2021-11-06", "2021-11-09", "1L 마시기"))
            add(ChallengeDTO("1", "나나", "1L 마시기", "2021-11-06", "2021-11-09", "1L 마시기"))

        }

//        cList2.apply {
//            add(ChallengeDTO("은결이한테 질척거리기", "2021-11-06~2021-11-09"))
//            add(ChallengeDTO("영진이 놀리기", "2021-11-06~2021-11-30"))
//            add(ChallengeDTO("은빈언니한테 물어보기", "2021-11-06~2021-11-09"))
//            add(ChallengeDTO("영진이 놀리기", "2021-11-06~2021-11-30"))
//            add(ChallengeDTO("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
//            add(ChallengeDTO("영진이 놀리기", "2021-11-06~2021-11-30"))
//            add(ChallengeDTO("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
//            add(ChallengeDTO("영진이 놀리기", "2021-11-06~2021-11-30"))
//        }

        binding.bestList.adapter = cListAdapter2
//        binding.allList.adapter = cListAdapter2
        binding.bestList.layoutManager = GridLayoutManager(context, 2)
        binding.allList.layoutManager = GridLayoutManager(context, 2)

    }
}