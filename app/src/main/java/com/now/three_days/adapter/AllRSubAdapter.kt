package com.now.three_days.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.Challenge
import com.now.three_days.data.model.Relay
import com.now.three_days.databinding.ItemListBinding
import android.graphics.drawable.GradientDrawable

import android.R
import android.widget.LinearLayout

import android.widget.RelativeLayout




class AllRSubAdapter(private val rList: List<Relay>) :RecyclerView.Adapter<AllRSubAdapter.RSubViewHolder>() {
    class RSubViewHolder(private val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(relay: Relay) {
            binding.allRTitle.text = relay.title
//            binding.date.text = relay.date
//            binding.border.setBackgroundResource(com.now.three_days.R.drawable.bg_custom_text_box_d)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RSubViewHolder {
        return RSubViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RSubViewHolder, position: Int) {
        holder.bind(rList[position])
    }

    override fun getItemCount(): Int {

        return rList.size
    }
}