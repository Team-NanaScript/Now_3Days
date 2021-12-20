package com.now.three_days.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.ChallengeVO
import com.now.three_days.databinding.ItemCListBinding

class CListAdapter(private val cList : List<ChallengeVO>) : RecyclerView.Adapter<CListAdapter.BLViewHolder>(){


    class BLViewHolder(private val binding:ItemCListBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ChallengeVO) {

            binding.cTitle.text = item.c_title
            binding.cDate.text = item.c_sDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BLViewHolder {
        val binding = ItemCListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BLViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BLViewHolder, position: Int) {
        holder.bind(cList[position])

        holder.itemView.setOnClickListener(View.OnClickListener {
            Log.d("Challenge 클릭 {}",position.toString())
        })
    }

    override fun getItemCount(): Int {
        return cList.size
//        if(cList.size >= 2) 2 else 0
    }
}