package com.now.three_days.ui.insert

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.navigation.fragment.R
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.now.three_days.MainActivity
import com.now.three_days.data.model.ChallengeVO
import com.now.three_days.databinding.InsertFragmentBinding
import com.now.three_days.databinding.MainActivityBinding
import com.now.three_days.databinding.MainFragmentBinding
import com.now.three_days.service.FireService
import com.now.three_days.service.impl.ChallengeServiceImplV1
import com.now.three_days.ui.main.MainFragment

class InsertFragment() : Fragment() {

    companion object {
        fun newInstance() = InsertFragment()
    }

    private lateinit var cs:ChallengeServiceImplV1
    private lateinit var viewModel: InsertViewModel
    private var _binding:InsertFragmentBinding ? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = InsertFragmentBinding.inflate(inflater, container, false)

        // select_box(spinner) 바인딩 후 어댑터 연결
        var spinner:Spinner = binding.insertSpinner
        var button: Button =  binding.btnSend

        val array = resources.getStringArray(com.now.three_days.R.array.array_category)

        // 프래그먼트에서 사용하기 위해 context 가 아닌 requireContext 를 파라메터로 지정
        val spinnerAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, array
        )
        spinner.adapter = spinnerAdapter

        button.setOnClickListener(View.OnClickListener {
            cs = ChallengeServiceImplV1()
            onClick(binding)
            cs.select()
        })

        return binding.root
    }

    // VO에 데이터 넣고 db에 추가하는 method
    fun onClick(binding: InsertFragmentBinding){
        val title:String = binding.title.text.toString()
        val date:String = binding.date.text.toString()
        val content:String = binding.content.text.toString()

        // select box
        val spinner:Spinner = binding.insertSpinner
        val select_text = spinner.selectedItem.toString()

        // 12-16일자 토스트 안나옴 왜 ?
        if(select_text == "" || select_text == null){
            Toast.makeText(requireContext(), "카테고리를 선택하세요 !", Toast.LENGTH_SHORT )
            return
        }
        if(title == "" || title == null){
            Toast.makeText(requireContext(), "제목을 입력하세요 !", Toast.LENGTH_SHORT )
            return
        }
        if(date == "" || date == null){
            Toast.makeText(requireContext(), "날짜를 선택하세요 !", Toast.LENGTH_SHORT )
            return
        }

        var challenge:ChallengeVO = ChallengeVO(
            c_title = title,
            c_content = content,
            c_sDate = date,
            c_userId = "영진",
            c_eDate = "",
            c_progress = false,
            c_image = "",
        )
        cs.insert(challenge, select_text)

        binding.title.setText("")
        binding.date.setText("")
        binding.content.setText("")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InsertViewModel::class.java)
        // TODO: Use the ViewModel
    }

}