package com.mad.cw21997

import android.app.Application
import com.mad.cw21997.data.AppContainer
import com.mad.cw21997.data.DefaultAppContainer

class TentApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
