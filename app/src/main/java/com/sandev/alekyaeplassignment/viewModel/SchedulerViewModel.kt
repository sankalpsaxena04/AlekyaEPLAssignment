package com.sandev.alekyaeplassignment.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandev.alekyaeplassignment.Domain.HelperClasses.PrefManager
import com.sandev.alekyaeplassignment.Domain.Models.Event
import com.sandev.alekyaeplassignment.UI.CalendarAdapter
import com.sandev.alekyaeplassignment.UI.EventAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class SchedulerViewModel @Inject constructor() : ViewModel() {

    private val _events = mutableListOf<Event>()
    val events: List<Event> get() = _events

    val eventAdapter = EventAdapter(_events)
    lateinit var calendarAdapter: CalendarAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    private var selectedMonth: YearMonth = YearMonth.now()

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadCalendar() {
        val days = generateDaysForMonth(selectedMonth)
        calendarAdapter = CalendarAdapter(days)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateDaysForMonth(month: YearMonth): List<String> {
        val days = mutableListOf<String>()
        for (day in 1..month.lengthOfMonth()) {
            days.add(day.toString())
        }
        return days
    }

    fun loadEvents() {
        viewModelScope.launch {
            _events.clear()
            _events.addAll(withContext(Dispatchers.IO) { PrefManager.getEvents() })
            eventAdapter.notifyDataSetChanged()
        }
    }

    fun saveEvent(event: Event) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _events.add(event)
                PrefManager.saveEvents(_events)
            }
            eventAdapter.notifyDataSetChanged()
        }
    }
}