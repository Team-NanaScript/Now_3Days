package com.now.three_days.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.Challenge
import com.now.three_days.data.model.Relay
import com.now.three_days.databinding.ItemListBinding

class AllCSubAdapter(private val cList: List<Challenge>) :RecyclerView.Adapter<AllCSubAdapter.CSubViewHolder>() {
    class CSubViewHolder(private val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(challenge: Challenge) {
            binding.allRTitle.text = challenge.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CSubViewHolder {
        return CSubViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CSubViewHolder, position: Int) {
        holder.bind(cList[position])
    }

    override fun getItemCount(): Int {

        return cList.size
    }
}