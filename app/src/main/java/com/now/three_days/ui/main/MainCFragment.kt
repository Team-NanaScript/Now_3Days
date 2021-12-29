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
import com.now.three_days.adapter.CListAdapter
import com.now.three_days.data.model.ChallengeDTO
import com.now.three_days.data.viewmodel.CListViewModel
import com.now.three_days.databinding.MainCFragmentBinding
import com.now.three_days.ui.AuthFragmentParent
import java.util.*
import kotlin.collections.ArrayList

class MainCFragment : AuthFragmentParent() {

    //    lateinit var listAdapter: ListAdapter
    private lateinit var cListAdapter: CListAdapter
    private lateinit var mainActivity: MainActivity


    companion object {
        fun newInstance() = MainCFragment()
    }

    // ====== list ======
    private lateinit var viewModel: CListViewModel
    private val mainCList = ArrayList<ChallengeDTO>()
    private var _binding: MainCFragmentBinding? = null
    private val binding get() = _binding!!


    // mainFragment에서 만들어둔 view를 보여주도록 연결하기
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainCFragmentBinding.inflate(inflater, container, false)

        val mainAct = activity as MainActivity
        mainAct?.setBottomNav(true)

//        rlistView = ViewModelProvider(this).get(CListViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CListViewModel::class.java)

        val mainActivity = activity as MainActivity
        val userId = mainActivity.getFile().userId.toString()
        Log.d("현재 userId", "$userId")

        viewModel.list().observe(viewLifecycleOwner, Observer {

            cListAdapter = CListAdapter(it as ArrayList<ChallengeDTO>)

            binding.cList.adapter = cListAdapter
            Log.d("mainRList", "$it")


            cListAdapter.setItemClickListener(object : CListAdapter.OnItemClcikListener {
                override fun onClick(view: View, position: Int) {
                    Log.d("position", position.toString())

                    var seq = it[position].c_seq
                    Log.d("seq", "$seq")

                    val bundle = bundleOf("seq" to seq)
                    findNavController().navigate(R.id.c_detail_page, bundle)
                }

            })

        })
        // TODO: Use the ViewModel
    }

}