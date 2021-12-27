package com.now.three_days.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.List_Data
import com.now.three_days.data.model.RelayDTO
import com.now.three_days.databinding.ItemMainListBinding

class ListAdapter(private val mainList: ArrayList<RelayDTO>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var data = mutableListOf<List_Data>()

    class ListViewHolder(private val binding: ItemMainListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: List_Data) {
            binding.tvTitle.text = item.tv_title
            binding.tvContent.text = item.tv_content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemMainListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        // holder에 데이터 연결
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}