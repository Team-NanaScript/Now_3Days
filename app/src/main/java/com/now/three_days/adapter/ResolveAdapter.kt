package com.now.three_days.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.ResolveVO
import com.now.three_days.databinding.ItemResolveListBinding

class ResolveAdapter(private val resolveList: ArrayList<ResolveVO>) :
    RecyclerView.Adapter<ResolveAdapter.ResViewHolder>() {
    class ResViewHolder(private val binding: ItemResolveListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResolveVO) {
            binding.rsUserId.text = item.rs_userId
            binding.rsContent.text = item.rs_content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResViewHolder {
        val binding =
            ItemResolveListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ResViewHolder, position: Int) {
        holder.bind(resolveList[position])
    }

    override fun getItemCount(): Int {
        return resolveList.size
    }
}