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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainCFragment : AuthFragmentParent() {

    private lateinit var cListAdapter: CListAdapter

    companion object {
        fun newInstance() = MainCFragment()
    }

    private lateinit var viewModel: CListViewModel
    private var _binding: MainCFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainCFragmentBinding.inflate(inflater, container, false)

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

        val localDate = LocalDateTime.now()
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.KOREA)
        val date = localDate.format(dateFormat)

        viewModel.listByUserIdAndDate(userId,date).observe(viewLifecycleOwner, Observer {

            cListAdapter = CListAdapter(it as ArrayList<ChallengeDTO>)

            binding.cList.adapter = cListAdapter
            Log.d("mainCList", "$it")

            cListAdapter.setItemClickListener(object : CListAdapter.OnItemClcikListener {
                override fun onClick(view: View, position: Int) {

                    var seq = it[position].c_seq

                    val bundle = bundleOf("seq" to seq)
                    findNavController().navigate(R.id.c_detail_page, bundle)
                }

            })

        })

    }

}