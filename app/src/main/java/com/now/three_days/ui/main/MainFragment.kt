package com.now.three_days.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.now.three_days.adapter.ListAdapter
import com.now.three_days.data.model.List_Data
import com.now.three_days.databinding.MainFragmentBinding
import com.now.three_days.ui.AuthFragmentParent

class MainFragment : AuthFragmentParent() {

    lateinit var listAdapter : ListAdapter
    val data = mutableListOf<List_Data>()

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding : MainFragmentBinding? = null

    private val binding get() = _binding!!

    // mainFragment에서 만들어둔 view를 보여주도록 연결하기
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = ListAdapter(this)

        data.apply {
            add(List_Data("","1L 마시기", "1L 마시기"))
            add(List_Data("","1km 달리기", "1km 달리기"))
            add(List_Data("","1시간 취미생활 즐기기", "1시간 취미생활 즐기기"))
            add(List_Data("","은결이 칭찬하기", "은결이 칭찬하기"))
            add(List_Data("","영진이 괴롭히기", "영진이 괴롭히기"))

            // 만들어둔 Adapter에 data 연결하기
            listAdapter.data = data
        }

        // 리사이클러뷰 adapter를 만들어놓은 ListAdapter로 사용하겠다
        binding.rcList.adapter = listAdapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}