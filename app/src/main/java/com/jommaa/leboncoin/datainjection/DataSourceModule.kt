package com.jommaa.leboncoin.datainjection

import com.jommaa.data.db.datasource.AlbumsDataSource
import com.jommaa.leboncoin.LeboncoinApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideDataSourceModule(app:LeboncoinApp):AlbumsDataSource{
        return AlbumsDataSource(app)
    }
}