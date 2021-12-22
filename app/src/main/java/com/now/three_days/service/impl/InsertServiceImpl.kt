package com.now.three_days.service.impl

import android.content.Context
import android.widget.Spinner
import android.widget.Toast
import com.now.three_days.MainActivity
import com.now.three_days.data.model.ChallengeVO
import com.now.three_days.data.model.RelayVO
import com.now.three_days.databinding.InsertFragmentBinding
import com.now.three_days.service.InsertService

class InsertServiceImpl : InsertService{

    private lateinit var cs: ChallengeServiceImplV1
    private lateinit var rs: RelayServiceImplV1

    override fun onClick(binding: InsertFragmentBinding, mainActivity: MainActivity, context:Context) {

        // 작성자 아이디
        val userId = mainActivity.getFile().userId.toString()

        val title:String = binding.title.text.toString()
        val sDate:String = binding.sDate.text.toString()
        val eDate:String = binding.eDate.text.toString()
        val content:String = binding.content.text.toString()

        // select box
        val spinner: Spinner = binding.insertSpinner
        val select_text = spinner.selectedItem.toString()

        if(select_text == "카테고리" || select_text == null){
            Toast.makeText(context, "카테고리를 선택하세요 !", Toast.LENGTH_SHORT ).show()
            return
        }
        if(title == "" || title == null){
            Toast.makeText(context, "제목을 입력하세요 !", Toast.LENGTH_SHORT ).show()
            return
        }
        if(eDate == "" || eDate == null){
            Toast.makeText(context, "날짜를 선택하세요 !", Toast.LENGTH_SHORT ).show()
            return
        }

        this.insert(select_text, title, content, sDate, eDate, userId)

        binding.title.setText("")
        binding.eDate.setText("")
        binding.content.setText("")
    }

    override fun insert(
        select_text: String,
        title: String,
        content: String,
        sDate: String,
        eDate: String,
        userId: String
    ) {

        cs = ChallengeServiceImplV1()
        rs = RelayServiceImplV1()

        if(select_text == "챌린지"){
            var challenge: ChallengeVO = ChallengeVO(
                c_title = title,
                c_content = content,
                c_sDate = sDate,
                c_userId = userId,
                c_eDate = eDate,
                c_progress = false,
                c_image = "",
            )
            cs = ChallengeServiceImplV1()
            cs.insert(challenge, select_text)
        }
        else if(select_text == "릴레이"){
            var relay: RelayVO = RelayVO(
                r_title = title,
                r_content = content,
                r_sDate = sDate,
                r_userId = userId,
                r_eDate = eDate,
                r_image = "",
            )
            rs = RelayServiceImplV1()
            rs.insert(relay , select_text)
        }
    }


}