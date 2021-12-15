package com.now.three_days.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.List_Data
import com.now.three_days.databinding.ItemMainListBinding
import com.now.three_days.ui.main.MainFragment


class ListAdapter() : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){
    // private val 를 사용해야 가져와서 사용할 수 있음
    // item과 ViewHolder를 연결, list_data를 연동

    var data = mutableListOf<List_Data>()
    class ListViewHolder(private val binding: ItemMainListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : List_Data) {
            binding.tvTitle.text = item.tv_title
            binding.tvContent.text = item.tv_content
        }
    }

    // view 공간 만들기, fragment에서 보여주도록 설정할 곳, inflate
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemMainListBinding.inflate(LayoutInflater.from(parent.context), parent , false)

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