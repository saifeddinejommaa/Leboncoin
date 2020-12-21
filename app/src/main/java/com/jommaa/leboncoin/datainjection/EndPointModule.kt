package com.jommaa.leboncoin.datainjection

import com.google.gson.Gson
import com.jommaa.data.EndPoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class EndPointModule {
    @Singleton
    @Provides
    fun providesEndPointModule(retrofit: Retrofit): EndPoint {
        return retrofit.create(EndPoint::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://static.leboncoin.fr/")
            .build()
    }
}