package com.now.three_days.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.MainListFragment
import com.now.three_days.data.model.Challenge
import com.now.three_days.data.model.Relay
import com.now.three_days.databinding.ItemListBinding

class AllSubAdapter(mainListFragment : MainListFragment ) :RecyclerView.Adapter<AllSubAdapter.SubViewHolder>() {
    class SubViewHolder(binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(challenge: Challenge , relay: Relay) {

            challenge.title
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