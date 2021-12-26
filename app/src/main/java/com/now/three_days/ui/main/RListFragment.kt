package com.now.three_days.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.now.three_days.MainActivity
import com.now.three_days.R
import com.now.three_days.adapter.RListAdapter
import com.now.three_days.data.model.RelayDTO
import com.now.three_days.databinding.RListFragmentBinding

class RListFragment : Fragment() {

    lateinit var rListAdapter1: RListAdapter
    lateinit var rListAdapter2: RListAdapter

    var mainActivity : MainActivity? = null

    private val rList1 = ArrayList<RelayDTO>()

    private var _binding: RListFragmentBinding? = null

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

        viewModel = ViewModelProvider(requireActivity()).get(RListViewModel::class.java)

        viewModel.list().observe(viewLifecycleOwner, Observer {
            Log.d("ViewModel {}", "$it")

            rListAdapter1 = RListAdapter(it)
            binding.allList.adapter = rListAdapter1


            rListAdapter1.setItemClickListener(object : RListAdapter.OnItemClcikListener {
                override fun onClick(view: View, position: Int) {
                    Log.d("position", position.toString())

                    Log.d("데이터", "$it")

                    var id = it[id]
                    Log.d("id", "$id")

                    findNavController().navigate(R.id.detail_page)
                }
            })
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

        binding.bestList.adapter = rListAdapter2
        binding.allList.layoutManager = GridLayoutManager(context, 2)
        binding.bestList.layoutManager = GridLayoutManager(context, 2)



    }


}