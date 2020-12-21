package com.jommaa.leboncoin

import android.app.Application
import com.jommaa.leboncoin.datainjection.ApplicationComponent
import com.jommaa.leboncoin.datainjection.ApplicationModule
import com.jommaa.leboncoin.datainjection.DaggerApplicationComponent

class LeboncoinApp : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        build()
    }

    fun build() {
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}