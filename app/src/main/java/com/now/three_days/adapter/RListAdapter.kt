package com.now.three_days.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.RelayVO
import com.now.three_days.databinding.ItemRListBinding

class RListAdapter(private val aList : List<RelayVO>)
    : RecyclerView.Adapter<RListAdapter.ALViewHolder>()  {

    class ALViewHolder(private val binding:ItemRListBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : RelayVO) {
            binding.rTitle.text = item.r_title
            binding.rDate.text = item.r_sDate
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ALViewHolder {
        val binding = ItemRListBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ALViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ALViewHolder, position: Int) {
        holder.bind(aList[position])

        holder.itemView.setOnClickListener(View.OnClickListener {

            // 어떤 item을 클릭했는지 로그 찍어보기기
           Log.d("Relay 클릭? {}",position.toString())
        })
    }

    override fun getItemCount(): Int {
       return aList.size
    }




}