package com.now.three_days

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.now.three_days.data.AllListAdapter
import com.now.three_days.data.SectionModel
import com.now.three_days.data.model.Challenge
import com.now.three_days.data.model.Relay
import com.now.three_days.databinding.MainListFragmentBinding

class MainListFragment : Fragment() {

    private val modelList = ArrayList<SectionModel>()

    private var _binding: MainListFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainListFragment()
    }

    private lateinit var viewModel: MainListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainListViewModel::class.java)
        // TODO: Use the ViewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 가상데이터 만들어주기
        val bestCategory = "인기"
        val cbList = ArrayList<Challenge>()
        val rbList = ArrayList<Relay>()
        cbList.add(Challenge("1L 마시기", "2021-11-06~2021-11-09"))
        rbList.add(Relay("1KM 달리기", "2021-11-06~2021-11-30"))

        val listCategory = "도전! 작심삼일"
        val clList = ArrayList<Challenge>()
        val rlList = ArrayList<Relay>()
        cbList.add(Challenge("은결이한테 질척거리기", "2021-11-06~2021-11-09"))
        cbList.add(Challenge("은빈언니한테 물어보기", "2021-11-06~2021-11-09"))
        cbList.add(Challenge("소연이랑 짜허하기", "2021-11-06~2021-11-09"))
        rbList.add(Relay("영진이 놀리기", "2021-11-06~2021-11-30"))
        rbList.add(Relay("영진이 놀리기", "2021-11-06~2021-11-30"))
        rbList.add(Relay("영진이 놀리기", "2021-11-06~2021-11-30"))

        // 가상데이터 만들어준 것들 SectionModel에 넣어주기
        modelList.add(SectionModel(bestCategory, cbList, rbList))
        modelList.add(SectionModel(listCategory, clList, rlList))

    }

    private fun setupRecyclerView() {
        binding.allList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context,2)
            adapter = AllListAdapter(modelList)
        }
    }
}