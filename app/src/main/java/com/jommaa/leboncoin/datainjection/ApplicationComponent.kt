package com.jommaa.leboncoin.datainjection

import android.app.Activity
import com.jommaa.leboncoin.LeboncoinApp
import com.jommaa.leboncoin.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules=[ApplicationModule::class,RepositoryModule::class,EndPointModule::class,DataSourceModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)
}