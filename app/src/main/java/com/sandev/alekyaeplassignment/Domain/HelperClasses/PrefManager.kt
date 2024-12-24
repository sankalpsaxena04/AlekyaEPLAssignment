package com.sandev.alekyaeplassignment.Domain.HelperClasses

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sandev.alekyaeplassignment.Domain.Models.Event

object PrefManager {
    private const val PREFS_NAME = "event_prefs"
    private const val EVENTS_KEY = "events_list"

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor : SharedPreferences.Editor
    private val gson = Gson()
    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun saveEvents( events: List<Event>) {
        val json = Gson().toJson(events)
        editor.putString(EVENTS_KEY, json)
        editor.apply()
    }

    fun getEvents(): List<Event> {
        val json = sharedPreferences.getString(EVENTS_KEY, null)
        return if (json != null) {
            val type = object : TypeToken<List<Event>>() {}.type
            Gson().fromJson(json, type)
        } else {
            emptyList()
        }
    }

}