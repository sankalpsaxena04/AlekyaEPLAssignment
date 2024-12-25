package com.sandev.alekyaeplassignment

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.sandev.alekyaeplassignment.Domain.Models.Event
import com.sandev.alekyaeplassignment.viewModel.SchedulerViewModel
import com.sandev.alekyaeplassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: SchedulerViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Initialize RecyclerViews
        binding.eventListRecyclerView.adapter = viewModel.eventAdapter
        viewModel.loadCalendar()
        binding.calendarRecyclerView.adapter = viewModel.calendarAdapter

        setupTimeSpinners()

        viewModel.loadEvents()

        binding.doneButton.setOnClickListener {
            val selectedHour = binding.hourSpinner.selectedItem.toString()
            val selectedMinute = binding.minuteSpinner.selectedItem.toString()
            val selectedAmPm = binding.ampmSpinner.selectedItem.toString()
            val selectedTime = "$selectedHour:$selectedMinute $selectedAmPm"

            val selectedDate = "2024-12-24"
            val newEvent = Event(
                title = "New Event",
                attendees = listOf("Attendee 1"),
                date = selectedDate,
                time = selectedTime,
                color = getRandomBrightColor()
            )
            viewModel.saveEvent(newEvent)
        }
    }
    fun getRandomBrightColor(): String {
        val random = Random.Default
        val red = random.nextInt(128, 256)
        val green = random.nextInt(128, 256)
        val blue = random.nextInt(128, 256)

        return String.format("#%02X%02X%02X", red, green, blue)
    }
    private fun setupTimeSpinners() {
        val hours = (1..12).map { it.toString().padStart(2, '0') }
        val minutes = (0..59).map { it.toString().padStart(2, '0') }
        val amPm = listOf("AM", "PM")

        setupSpinner(binding.hourSpinner, hours)
        setupSpinner(binding.minuteSpinner, minutes)
        setupSpinner(binding.ampmSpinner, amPm)
    }

    private fun setupSpinner(spinner: Spinner, items: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        spinner.adapter = adapter
    }
}