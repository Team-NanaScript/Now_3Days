package com.now.three_days.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.now.three_days.databinding.RelayDetailFragmentBinding
import com.now.three_days.ui.main.RListFragment
import com.now.three_days.ui.main.RListViewModel

class RelayDetailFragment : Fragment() {

    private lateinit var viewModel: RListViewModel
    private lateinit var rListFragment: RListFragment

    private var _binding:RelayDetailFragmentBinding? = null

    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = RelayDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이전 Fragment 에서 전송한 bundle 데이터 받기
        val bundle: Bundle? = arguments
        val seq = bundle?.get("seq")
        viewModel = ViewModelProvider(requireParentFragment())[RListViewModel::class.java]

        viewModel.list().observe(viewLifecycleOwner, Observer {
            val iSize = it.size-1
            for(i in 0..iSize){
                val r_seq = it[i].r_seq
                if(r_seq == seq){
                    binding.dTitle.text = it[i].r_title
                    binding.dContent.text = it[i].r_content
                }
            }
        })
    }
}

