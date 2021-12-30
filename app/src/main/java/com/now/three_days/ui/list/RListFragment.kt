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
import com.now.three_days.MainActivity
import com.now.three_days.R
import com.now.three_days.adapter.RListAdapter
import com.now.three_days.data.model.RelayDTO
import com.now.three_days.data.viewmodel.RListViewModel
import com.now.three_days.databinding.RListFragmentBinding

class RListFragment : Fragment() {

    lateinit var rListAdapter1: RListAdapter

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

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(RListViewModel::class.java)

        /**
         * 새고고침
         * 다시 데이터를 fetch 하기
         */
        binding.cSwipeLayout.setOnRefreshListener {
            // isRefreshing 새로고침 후 아이콘 없애기
            binding.cSwipeLayout.isRefreshing = false
            viewModel.list()
//            val item = it.shuffled()
//            Log.d("shuffled", "$item")
//
//            rListAdapter1 = RListAdapter(item)
//            binding.allList.adapter = rListAdapter1
        }
        
        
        viewModel.shuffle().observe(viewLifecycleOwner, Observer {
            Log.d("ViewModel {}", "$it")

            rListAdapter1 = RListAdapter(it)
            binding.allList.adapter = rListAdapter1


            rListAdapter1.setItemClickListener(object : RListAdapter.OnItemClcikListener {
                override fun onClick(view: View, position: Int) {
                    Log.d("position", position.toString())

                    var seq = it[position].r_seq
                    Log.d("seq", "$seq")

                    val bundle = bundleOf("seq" to seq)
                    findNavController().navigate(R.id.r_detail_page, bundle)
                }
            })
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.allList.layoutManager = GridLayoutManager(context, 2)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}