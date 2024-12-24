package com.sandev.alekyaeplassignment.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sandev.alekyaeplassignment.R


class CalendarAdapter(private val days: List<String>) : RecyclerView.Adapter<CalendarAdapter.CalendarDayViewHolder>() {

    class CalendarDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayTextView: TextView = itemView.findViewById(R.id.dayText)
        fun bind(day: String) {
            dayTextView.text = day
            dayTextView.setOnClickListener {
                dayTextView.setBackgroundColor(R.color.bg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarDayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar_day, parent, false)
        return CalendarDayViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarDayViewHolder, position: Int) {
        holder.bind(days[position])
    }

    override fun getItemCount(): Int = days.size
}