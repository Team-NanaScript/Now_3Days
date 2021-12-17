package com.now.three_days.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.CListData
import com.now.three_days.databinding.ItemCListBinding

class CListAdapter(private val cList : List<CListData>) : RecyclerView.Adapter<CListAdapter.BLViewHolder>(){


    class BLViewHolder(private val binding:ItemCListBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item : CListData) {

            binding.cTitle.text = item.c_rtitle
            binding.cDate.text = item.c_rdate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BLViewHolder {
        val binding = ItemCListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BLViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BLViewHolder, position: Int) {
        holder.bind(cList[position])
    }

    override fun getItemCount(): Int {
        return cList.size
//        if(cList.size >= 2) 2 else 0
    }
}