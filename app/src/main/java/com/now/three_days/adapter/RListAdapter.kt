package com.now.three_days.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.RelayDTO
import com.now.three_days.databinding.ItemRListBinding
import com.now.three_days.ui.main.DetailFragment

class RListAdapter(private val aList: List<RelayDTO>) :
    RecyclerView.Adapter<RListAdapter.ALViewHolder>() {

    class ALViewHolder(private val binding: ItemRListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RelayDTO) {
            binding.rTitle.text = item.r_title
            binding.rDate.text = item.r_sDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ALViewHolder {
        val binding = ItemRListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ALViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ALViewHolder, position: Int) {
        holder.bind(aList[position])

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.itemView?.context, DetailFragment::class.java)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
            // 어떤 item을 클릭했는지 로그 찍어보기기
            Log.d("Relay 클릭? {}", position.toString())
        })
    }

    override fun getItemCount(): Int {
        return aList.size
    }


}