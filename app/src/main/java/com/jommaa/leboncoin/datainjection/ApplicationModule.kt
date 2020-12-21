package com.jommaa.leboncoin.datainjection


import com.jommaa.leboncoin.LeboncoinApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: LeboncoinApp) {

    @Provides
    @Singleton
    fun provideApplication(): LeboncoinApp {
        return application
    }


}