package com.now.three_days.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.data.model.List_Data
import com.now.three_days.data.model.RListData
import com.now.three_days.databinding.ItemListBinding

//class RListAdapter(private val rList : List<RListData>)
class RListAdapter
    : RecyclerView.Adapter<RListAdapter.ALViewHolder>() {

    var rList = mutableListOf<RListData>()
    class ALViewHolder(private val binding:ItemListBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : RListData) {
            binding.title.text = item.a_ctitle
            binding.date.text = item.a_cdate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ALViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        Log.d("RListAdapter", "createViewHolder")
        return ALViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ALViewHolder, position: Int) {
        holder.bind(rList[position])
    }

    override fun getItemCount(): Int {
        Log.d("RListAdapter", rList.size.toString())
       return rList.size
    }


}