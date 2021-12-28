package com.now.three_days.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.now.three_days.MainActivity
import com.now.three_days.adapter.ResolveAdapter
import com.now.three_days.data.model.ResolveVO
import com.now.three_days.databinding.RelayDetailFragmentBinding
import com.now.three_days.data.viewmodel.RListViewModel
import com.now.three_days.data.viewmodel.ResolveViewModel
import com.now.three_days.service.impl.ResolveServiceImplV1
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList

class RelayDetailFragment : Fragment() {

    private lateinit var resListAdapter: ResolveAdapter

    private lateinit var viewModel: RListViewModel
    private lateinit var resolveViewModel: ResolveViewModel

    private lateinit var resService: ResolveServiceImplV1

    private var _binding: RelayDetailFragmentBinding? = null

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
            val iSize = it.size - 1
            for (i in 0..iSize) {
                val r_seq = it[i].r_seq
                if (r_seq == seq) {
                    binding.relayTitle.text = it[i].r_title
                    binding.relayDate.text = String.format("%s ~ %s", it[i].r_sDate, it[i].r_eDate)
                    binding.relayUserid.text = it[i].r_userId
                    binding.relayContent.text = it[i].r_content
                }
            }
        })

        resolveViewModel = ViewModelProvider(requireParentFragment())[ResolveViewModel::class.java]

//        resolveViewModel.list().observe(viewLifecycleOwner, Observer {
        resolveViewModel.listByRelaySeq(seq.toString()).observe(viewLifecycleOwner, Observer {
            Log.d("relay resolve list fragment", "$it")

            resListAdapter = ResolveAdapter(it as ArrayList<ResolveVO>)
            binding.relayResolveList.adapter = resListAdapter
        })

        binding.resolveSave.setOnClickListener {
            val rs_content = binding.resolveContent.text.toString()
            if (rs_content == null || rs_content == "") {
                Toast.makeText(context, "내용을 입력해주세요 !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val mainAct = activity as MainActivity
            val userId = mainAct.getFile().userId.toString()
//            val resSeq = String.format("%s%s",LocalTime.now(),userId)
            resService = ResolveServiceImplV1()
            val resVO = ResolveVO(
                resolveViewModel.resolveList.value!!.size + 1,
                seq.toString(),
                userId,
                rs_content
            )
            resService.insert(resVO, "resolve")
            binding.resolveContent.setText("")
        }
    }


}

