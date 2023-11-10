package com.klimpel.abschlussarbeitmodul3.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

// Annotation indicating that this class is an Android Application
@HiltAndroidApp
class PokedexApplication : Application() {
    // Method called when the application is created
    override fun onCreate() {
        // Call the onCreate method of the parent class
        super.onCreate()
        // Plant a Timber DebugTree to log messages
        Timber.plant(Timber.DebugTree())
    }
}