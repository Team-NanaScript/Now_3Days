package com.now.three_days.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.now.three_days.MainActivity
import com.now.three_days.R
import com.now.three_days.adapter.RListAdapter
import com.now.three_days.data.model.RelayDTO
import com.now.three_days.data.viewmodel.RListViewModel
import com.now.three_days.databinding.MainRFragmentBinding
import com.now.three_days.ui.AuthFragmentParent

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


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RListViewModel::class.java)

        viewModel.listByUserId("nanask").observe(viewLifecycleOwner, Observer {
            rListAdapter = RListAdapter(it as ArrayList<RelayDTO>)
            binding.rList.adapter = rListAdapter
            Log.d("mainRList", "$it")

            rListAdapter.setItemClickListener(object : RListAdapter.OnItemClcikListener {
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

}