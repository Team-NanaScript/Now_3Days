package com.now.three_days.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.SectionModel
import com.now.three_days.databinding.MainListFragmentBinding

class AllListAdapter(private val modelList : ArrayList<SectionModel>)
    : RecyclerView.Adapter<AllListAdapter.ALViewHolder>() {


    class ALViewHolder(private val binding:MainListFragmentBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(sectionModel : SectionModel) {

            binding.category.text = sectionModel.category
            binding.allList.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(binding.root.context, 2)
                adapter = AllSubAdapter(sectionModel.rList, sectionModel.cList)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ALViewHolder {
        return ALViewHolder(MainListFragmentBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ALViewHolder, position: Int) {
        holder.bind(modelList[position])
    }

    override fun getItemCount(): Int {
       return modelList.size
    }
}