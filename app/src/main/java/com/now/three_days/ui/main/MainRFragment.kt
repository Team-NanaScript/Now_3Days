package com.now.three_days.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.now.three_days.MainActivity
import com.now.three_days.adapter.RListAdapter
import com.now.three_days.data.model.RelayDTO
import com.now.three_days.data.viewmodel.RListViewModel
import com.now.three_days.databinding.MainRFragmentBinding
import com.now.three_days.ui.AuthFragmentParent
import kotlin.collections.ArrayList

class MainRFragment : AuthFragmentParent() {

    private lateinit var rListAdapter: RListAdapter

    companion object {
        fun newInstance() = MainRFragment()
    }

    // ====== list ======
    private lateinit var viewModel: RListViewModel
    private val mainRList = ArrayList<RelayDTO>()
    private var _binding: MainRFragmentBinding? = null
    private val binding get() = _binding!!



    // mainFragment에서 만들어둔 view를 보여주도록 연결하기
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainRFragmentBinding.inflate(inflater, container, false)

        val mainAct = activity as MainActivity
        mainAct?.setBottomNav(true)

//        clistView = ViewModelProvider(this).get(CListViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RListViewModel::class.java)

        viewModel.list().observe(viewLifecycleOwner, Observer{
            rListAdapter = RListAdapter(it as ArrayList<RelayDTO>)
            binding.rList.adapter = rListAdapter
            Log.d("mainRList","$it")
        })
        // TODO: Use the ViewModel
    }

}