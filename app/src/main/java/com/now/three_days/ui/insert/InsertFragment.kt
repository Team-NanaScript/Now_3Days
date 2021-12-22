package com.now.three_days.ui.insert

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
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.now.three_days.MainActivity
import com.now.three_days.data.model.ChallengeDTO
import com.now.three_days.data.model.ChallengeVO
import com.now.three_days.data.model.RelayVO
import com.now.three_days.databinding.InsertFragmentBinding
import com.now.three_days.service.FireService
import com.now.three_days.service.InsertService
import com.now.three_days.service.impl.ChallengeServiceImplV1
import com.now.three_days.service.impl.InsertServiceImpl
import com.now.three_days.service.impl.RelayServiceImplV1
import com.now.three_days.ui.main.MainFragment
import java.time.LocalDate
import java.time.Month
import java.time.Year

class InsertFragment() : Fragment() {

    companion object {
        fun newInstance() = InsertFragment()
    }

    private lateinit var insertService: InsertService

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
        val localToday = LocalDate.now()
        binding.sDate.setText(localToday.toString())

//        val year = LocalDate.now().year
//        val month = LocalDate.now().monthValue
//        val date = LocalDate.now().dayOfMonth

        // 종료일을 클릭했을 때
        binding.btnEDate.setOnClickListener{

            // 캘린더 날짜
            val gregorianCalendar = GregorianCalendar()
            val cYear = gregorianCalendar.get(Calendar.YEAR)
            val cMonth = gregorianCalendar.get(Calendar.MONTH)
            val cDay = gregorianCalendar.get(Calendar.DAY_OF_MONTH)

            // 종료일 날짜를 선택하기 위한 코드
            val calendar = DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view : DatePicker?, year: Int, month: Int, dayofMonth: Int) {
                    var selectDay = LocalDate.of(year ,month+1 ,dayofMonth)
                    binding.eDate.setText(selectDay.toString())
                }
            }, cYear, cMonth , cDay)

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
            val mainActivity = activity as MainActivity
            insertService = InsertServiceImpl()
            insertService.onClick(binding, mainActivity, requireContext())
        })
    }



}