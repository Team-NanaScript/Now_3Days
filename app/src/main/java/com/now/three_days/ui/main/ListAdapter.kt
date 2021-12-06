package com.now.three_days.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.list_data
import com.now.three_days.databinding.ItemMainListBinding


class ListAdapter(mainFragment: MainFragment) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(){
    // private val 를 사용해야 가져와서 사용할 수 있음
    // item과 ViewHolder를 연결, list_data를 연동

    var data = mutableListOf<list_data>()
    class ListViewHolder(private val binding: ItemMainListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : list_data) {
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