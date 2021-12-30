package com.now.three_days.ui.detail

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.transition.Visibility
import com.now.three_days.MainActivity
import com.now.three_days.adapter.CheerAdapter
import com.now.three_days.data.model.ChallengeVO
import com.now.three_days.data.model.CheerVO
import com.now.three_days.data.viewmodel.CListViewModel
import com.now.three_days.data.viewmodel.CheerViewModel
import com.now.three_days.databinding.ChallengeDetailFragmentBinding
import com.now.three_days.service.impl.CheerServiceImplV1
import com.now.three_days.service.impl.InsertServiceImpl
import java.time.LocalDate

class ChallengeDetailFragment : Fragment() {

    private lateinit var cheerAdapter: CheerAdapter

    private lateinit var viewModel: CListViewModel
    private lateinit var cheerViewModel: CheerViewModel

    private lateinit var cheerService: CheerServiceImplV1
    private lateinit var insertService: InsertServiceImpl

    // visible 라이브데이터 세팅
    var _isVisible:MutableLiveData<Boolean> = MutableLiveData()
    val isVisible:LiveData<Boolean>
                    get() = _isVisible

    init {
        _isVisible.value = true
    }

    private var _binding: ChallengeDetailFragmentBinding? = null

    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ChallengeDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        val userId = mainActivity.getFile().userId.toString()

        // 이전 Fragment 에서 전송한 bundle 데이터 받기
        val bundle: Bundle? = arguments

        val seq = bundle?.get("seq").toString()
        viewModel = ViewModelProvider(requireParentFragment())[CListViewModel::class.java]

        viewModel.detail(seq).observe(viewLifecycleOwner, Observer {
                    binding.challTitle.text = it.c_title
                    binding.challDate.text = String.format("%s ~ %s", it.c_sDate, it.c_eDate)
                    binding.challUserid.text = it.c_userId
                    binding.challContent.text = it.c_content

                    if(userId == it.c_userId) _isVisible.value = false else _isVisible.value = true
        })

        cheerViewModel = ViewModelProvider(requireParentFragment())[CheerViewModel::class.java]

        cheerViewModel.listByChallSeq(seq.toString()).observe(viewLifecycleOwner, Observer {
            Log.d("challenge cheer list fragment", "$it")

            cheerAdapter = CheerAdapter(it as ArrayList<CheerVO>)
            binding.challCheerList.adapter = cheerAdapter
        })

        binding.challInsert.setOnClickListener{

            var sDate = LocalDate.now()
            var eDate:LocalDate = sDate.plusDays(3)

            var title = binding.challTitle.text.toString()
            var content = binding.challContent.text.toString()

            insertService = InsertServiceImpl()
            insertService.insert("챌린지", title, content, sDate.toString(), eDate.toString(), userId)

            Toast.makeText(context, "챌린지 도전 !", Toast.LENGTH_SHORT).show()

        }

        binding.cheerSave.setOnClickListener {
            val ch_content = binding.cheerContent.text.toString()
            if (ch_content == null || ch_content == "") {
                Toast.makeText(context, "내용을 입력해주세요 !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mainAct = activity as MainActivity
            val userId = mainAct.getFile().userId.toString()

            cheerService = CheerServiceImplV1()
            val cheerVO = CheerVO(
                userId,
                ch_content
            )
//            cheerService.insert(cheerVO, "cheer")
            cheerService.insertComment("챌린지", seq.toString(), "댓글", cheerVO)
            binding.cheerContent.setText("")
        }
    }
}

