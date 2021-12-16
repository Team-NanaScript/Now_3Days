package com.now.three_days.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.CListData
import com.now.three_days.databinding.ItemBestListBinding

class CListAdapter(private val cList : List<CListData>) : RecyclerView.Adapter<CListAdapter.BLViewHolder>(){


    class BLViewHolder(private val binding:ItemBestListBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item : CListData) {

            binding.bestTitle.text = item.c_rtitle
            binding.bestDate.text = item.c_rdate
            binding.borderBest.setBackgroundResource(com.now.three_days.R.drawable.bg_custom_text_box_d)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BLViewHolder {
        val binding = ItemBestListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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