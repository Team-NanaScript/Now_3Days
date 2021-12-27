package com.now.three_days.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.now.three_days.R
import com.now.three_days.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    private lateinit var viewModel: RListViewModel
    private lateinit var rListFragment: RListFragment

    private var _binding: DetailFragmentBinding? = null

    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DetailFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireParentFragment()).get(RListViewModel::class.java)

        viewModel.list().observe(viewLifecycleOwner, Observer {

            Log.d("title", "$it")

            val tText:TextView = binding.dTitle
//            tText.setText(it.get().r_title)




        })
    }
}

