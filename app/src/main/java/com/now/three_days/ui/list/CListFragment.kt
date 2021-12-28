package com.now.three_days.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.now.three_days.R
import com.now.three_days.adapter.CListAdapter
import com.now.three_days.data.model.ChallengeDTO
import com.now.three_days.data.viewmodel.CListViewModel
import com.now.three_days.databinding.CListFragmentBinding
import java.util.*
import kotlin.collections.ArrayList


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

//            val random = Random()
//
//            for(i in 0..it.size) {
//
//            }

            cListAdapter1 = CListAdapter(it as ArrayList<ChallengeDTO>)
            binding.allList.adapter = cListAdapter1


            cListAdapter1.setItemClickListener(object : CListAdapter.OnItemClcikListener {
                override fun onClick(view: View, position: Int) {

                    var seq = it[position].c_seq
                    Log.d("seq", "$seq")

                    val bundle = bundleOf("seq" to seq)
                    findNavController().navigate(R.id.c_detail_page, bundle)

                }

            })

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cListAdapter2 = CListAdapter(cList1)


//        cList1.apply {
//            add(ChallengeDTO("1", "나나", "1L 마시기", "2021-11-06", "2021-11-09", "1L 마시기"))
//            add(ChallengeDTO("1", "나나", "1L 마시기", "2021-11-06", "2021-11-09", "1L 마시기"))
//
//        }

//        binding.bestList.adapter = cListAdapter2
//        binding.bestList.layoutManager = GridLayoutManager(context, 2)
        binding.allList.layoutManager = GridLayoutManager(context, 2)

    }
}