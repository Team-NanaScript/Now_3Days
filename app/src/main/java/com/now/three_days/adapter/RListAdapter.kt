package com.now.three_days.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.R
import com.now.three_days.data.model.RelayDTO
import com.now.three_days.databinding.ItemRListBinding

class RListAdapter(private val aList: List<RelayDTO>) :
    RecyclerView.Adapter<RListAdapter.ALViewHolder>() {



    class ALViewHolder(private val binding: ItemRListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RelayDTO) {
            binding.rTitle.text = item.r_title
            binding.rDate.text = item.r_sDate
            binding.cardView.setBackgroundResource(R.drawable.bg_custom_box)
//            binding.rSeq.text = item.r_seq
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ALViewHolder {
        val binding = ItemRListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ALViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ALViewHolder, position: Int) {
        holder.bind(aList[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }

    }

    override fun getItemCount(): Int {
        return aList.size
    }
    // Listener interface 생성
    interface OnItemClcikListener {
        fun onClick(view : View, position: Int)
    }
    // 외부에서 클릭시 이벤트 설정
    fun setItemClickListener(onItemClcikListener: OnItemClcikListener) {
        this.itemClickListener = onItemClcikListener
    }


    private lateinit var itemClickListener : OnItemClcikListener


}