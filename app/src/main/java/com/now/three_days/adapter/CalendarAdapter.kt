package com.now.three_days.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.now.three_days.R
import com.now.three_days.data.model.CalendarVO
import com.now.three_days.databinding.ItemCalendarListBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class CalendarAdapter(private val cList: List<CalendarVO>) :
    RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    class CalendarViewHolder(private val binding: ItemCalendarListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CalendarVO) {
            binding.date.text = item.cl_date
            binding.day.text = item.cl_day

            var today = binding.date.text

            val now = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("dd").withLocale(Locale.forLanguageTag("ko")))

            Log.d("today", "$today")

            if (today == now) {
                binding.weekCardview.setBackgroundResource(R.drawable.bg_custom_box)
            }
//            binding.date.setBackgroundResource()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding =
            ItemCalendarListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(cList[position])
    }

    override fun getItemCount(): Int {
        return cList.size
    }


}