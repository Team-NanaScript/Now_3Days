package com.now.three_days.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.R
import com.now.three_days.data.model.ChallengeDTO
import com.now.three_days.databinding.ItemCListBinding

class CListAdapter(private val cList: ArrayList<ChallengeDTO>) :
    RecyclerView.Adapter<CListAdapter.BLViewHolder>() {

    class BLViewHolder(private val binding: ItemCListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChallengeDTO) {
            binding.cTitle.text = item.c_title
            binding.cDate.text = item.c_sDate
            binding.cardView.setBackgroundResource(R.drawable.bg_custom_box)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BLViewHolder {
        val binding = ItemCListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BLViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BLViewHolder, position: Int) {
        holder.bind(cList[position])


        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }

    override fun getItemCount(): Int {
        return cList.size
    }

    interface OnItemClcikListener {
        fun onClick(view : View, position: Int)
    }

    fun setItemClickListener(onItemClcikListener: OnItemClcikListener) {
        this.itemClickListener = onItemClcikListener
    }


    private lateinit var itemClickListener : OnItemClcikListener
}