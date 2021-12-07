package com.now.three_days.ui.insert

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.now.three_days.R
import com.now.three_days.data.model.Challenge
import com.now.three_days.databinding.InsertFragmentBinding
import com.now.three_days.service.FireService

class InsertFragment : Fragment() {

    companion object {
        fun newInstance() = InsertFragment()
    }

    private val firebase: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val dbRef: DatabaseReference = firebase.reference

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

            Log.d("테스트", "클릭됨?")

            var title:EditText = binding.title
            var date:EditText = binding.date

            var challenge:Challenge = Challenge(title.text.toString(), date.text.toString())
            dbRef.child("테스트").setValue(challenge)


        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InsertViewModel::class.java)
        // TODO: Use the ViewModel
    }

}