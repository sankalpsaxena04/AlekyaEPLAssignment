package com.sandev.alekyaeplassignment.Domain.Models

data class Event(
    val title: String,
    val attendees: List<String>,
    val date: String,
    val time: String,
    val color: String
)