package com.sandev.alekyaeplassignment.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sandev.alekyaeplassignment.Domain.Models.Event
import com.sandev.alekyaeplassignment.R

class EventAdapter(private val events: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventTitle: TextView = itemView.findViewById(R.id.eventTitle)
        private val eventDate: TextView = itemView.findViewById(R.id.eventDate)
        private val eventTime: TextView = itemView.findViewById(R.id.eventTime)
        private val eventColor: View = itemView.findViewById(R.id.eventColor)
        fun bind(event: Event) {
            eventTitle.text = event.title
            eventDate.text = event.date
            eventTime.text = event.time
            eventColor.setBackgroundColor(android.graphics.Color.parseColor(event.color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size
}