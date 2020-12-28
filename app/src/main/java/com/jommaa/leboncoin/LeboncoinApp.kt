package com.jommaa.leboncoin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LeboncoinApp : Application() {

    //lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        //build()
    }
    /*
    fun build() {
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
    */

}