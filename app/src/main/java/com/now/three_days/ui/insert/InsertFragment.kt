package com.now.three_days.ui.insert

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.now.three_days.data.model.ChallengeVO
import com.now.three_days.databinding.InsertFragmentBinding
import com.now.three_days.service.FireService
import com.now.three_days.service.impl.ChallengeServiceImplV1

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

        var button: Button =  binding.btnSend
        button.setOnClickListener(View.OnClickListener {
            onClick(binding)
        })

        return binding.root
    }

    // VO에 데이터 넣고 db에 추가하는 method
    fun onClick(binding: InsertFragmentBinding){
        var title:EditText = binding.title
        var date:EditText = binding.date
        var content:EditText = binding.content

        var challenge:ChallengeVO = ChallengeVO(
            c_title = title.text.toString(),
            c_content = content.text.toString(),
            c_sDate = date.text.toString(),
            c_userId = "영진",
            c_eDate = "",
            c_progress = false,
            c_image = "",
        )
        cs = ChallengeServiceImplV1()
        cs.insert(challenge, "challenge")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InsertViewModel::class.java)
        // TODO: Use the ViewModel
    }

}