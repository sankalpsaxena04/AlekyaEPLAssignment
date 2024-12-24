package com.sandev.alekyaeplassignment

import android.app.Application
import com.sandev.alekyaeplassignment.Domain.HelperClasses.PrefManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlekyaApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        PrefManager.initialize(this@AlekyaApplication)
    }
}