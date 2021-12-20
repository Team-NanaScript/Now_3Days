package com.now.three_days.ui.insert

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
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

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InsertViewModel::class.java)

        // insert 에서 시작일을 오늘 날짜로 보여주기
        val today = GregorianCalendar()
        val year = today.get(Calendar.YEAR)
        val month = today.get(Calendar.MONTH)
        val date = today.get(Calendar.DATE)
        // 왜인지 모르겠으나 month+1 안하면 날짜가 오늘 날짜로 안나옴..
        binding.sDate.setText("${year}년 ${month + 1}월 ${date}일")

        // 종료일을 클릭했을 때
        binding.btnEDate.setOnClickListener{

            // 종료일 날짜를 선택하기 위한 코드
            val calendar = DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view : DatePicker?, y : Int, m : Int, dayofMonth: Int) {
                    binding.eDate.setText("${ y }년 ${ m +1 }월 ${ dayofMonth }일")
                }
            }, year, month, date)

            calendar.show()
        }

        // select_box(spinner) 바인딩 후 어댑터 연결
        var spinner:Spinner = binding.insertSpinner
        var button: Button =  binding.btnSend

        val array = resources.getStringArray(com.now.three_days.R.array.array_category)

        // 프래그먼트에서 사용하기 위해 context 가 아닌 requireContext 를 파라메터로 지정
        val spinnerAdapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item, array
        )
        spinner.adapter = spinnerAdapter

        // 저장 버튼 클릭
        button.setOnClickListener(View.OnClickListener {
            cs = ChallengeServiceImplV1()
            onClick(binding)
            cs.select()
        })
    }

    // VO에 데이터 넣고 db에 추가하는 method
    fun onClick(binding: InsertFragmentBinding){
        val title:String = binding.title.text.toString()
        val sDate:String = binding.sDate.text.toString()
        val eDate:String = binding.eDate.text.toString()
        val content:String = binding.content.text.toString()

        // select box
        val spinner:Spinner = binding.insertSpinner
        val select_text = spinner.selectedItem.toString()

        if(select_text == "" || select_text == null){
            Toast.makeText(requireContext(), "카테고리를 선택하세요 !", Toast.LENGTH_SHORT ).show()
            return
        }
        if(title == "" || title == null){
            Toast.makeText(requireContext(), "제목을 입력하세요 !", Toast.LENGTH_SHORT ).show()
            return
        }
        if(eDate == "" || eDate == null){
            Toast.makeText(requireContext(), "날짜를 선택하세요 !", Toast.LENGTH_SHORT ).show()
            return
        }

        var challenge:ChallengeVO = ChallengeVO(
            c_title = title,
            c_content = content,
            c_sDate = sDate,
            c_userId = "영진",
            c_eDate = eDate,
            c_progress = false,
            c_image = "",
        )
        cs.insert(challenge, select_text)

        binding.title.setText("")
        binding.eDate.setText("")
        binding.content.setText("")
    }

}