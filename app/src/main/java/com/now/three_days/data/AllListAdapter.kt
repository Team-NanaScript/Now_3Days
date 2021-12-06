package com.now.three_days.data

import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.MainListFragment
import com.now.three_days.databinding.MainListFragmentBinding

class AllListAdapter(private val modelList : ArrayList<SectionModel>) : RecyclerView.Adapter<AllListAdapter.ALViewHolder>() {


    class ALViewHolder(private val binding:MainListFragmentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sectionModel : SectionModel ) {

            binding.category.text = sectionModel.category
            binding.allList.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(binding.root.context, 2)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ALViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ALViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}