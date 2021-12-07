package com.now.three_days.ui.insert

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.now.three_days.R
import com.now.three_days.databinding.InsertFragmentBinding

class InsertFragment : Fragment() {

    companion object {
        fun newInstance() = InsertFragment()
    }

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



        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InsertViewModel::class.java)
        // TODO: Use the ViewModel
    }

}