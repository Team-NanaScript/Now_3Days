package com.now.three_days.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.CheerVO
import com.now.three_days.databinding.ItemCheerListBinding

class CheerAdapter(private val cheerList: ArrayList<CheerVO>) :
    RecyclerView.Adapter<CheerAdapter.CheerViewHolder>() {
    class CheerViewHolder(private val binding: ItemCheerListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CheerVO) {
            binding.chUserId.text = item.ch_userId
            binding.chContent.text = item.ch_content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheerViewHolder {
        val binding =
            ItemCheerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CheerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheerViewHolder, position: Int) {
        holder.bind(cheerList[position])
    }

    override fun getItemCount(): Int {
        return cheerList.size
    }
}