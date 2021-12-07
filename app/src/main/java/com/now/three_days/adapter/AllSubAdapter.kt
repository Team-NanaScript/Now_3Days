package com.now.three_days.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.Challenge
import com.now.three_days.data.model.Relay
import com.now.three_days.databinding.ItemListBinding

class AllSubAdapter(private val cList: List<Relay>, private val rList: List<Challenge>) :RecyclerView.Adapter<AllSubAdapter.SubViewHolder>() {
    class SubViewHolder(private val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(challenge: Challenge , relay: Relay) {
            binding.allCTitle.text = challenge.title
            binding.allRTitle.text = relay.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SubViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}