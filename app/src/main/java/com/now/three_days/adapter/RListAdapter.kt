package com.now.three_days.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.RListData
import com.now.three_days.databinding.ItemRListBinding

class RListAdapter(private val aList : List<RListData>)
    : RecyclerView.Adapter<RListAdapter.ALViewHolder>() {

    class ALViewHolder(private val binding:ItemRListBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : RListData) {
            binding.rTitle.text = item.a_ctitle
            binding.rDate.text = item.a_cdate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ALViewHolder {
        val binding = ItemRListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ALViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ALViewHolder, position: Int) {
        holder.bind(aList[position])
    }

    override fun getItemCount(): Int {
       return aList.size
    }


}