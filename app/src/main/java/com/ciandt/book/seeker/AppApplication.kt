package com.ciandt.book.seeker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application() {
    @Override
    override fun onCreate() {
        super.onCreate()
    }
}