package com.jommaa.leboncoin.datainjection

import com.jommaa.data.api.LeboncoinApi
import com.jommaa.data.db.datasource.AlbumsDataSource
import com.jommaa.data.mapper.AlbumMapper
import com.jommaa.data.repositoryImp.AlbumsRepositoryImp
import com.jommaa.domain.Repository.IAlbumsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAlbumsRepository(api: LeboncoinApi,dataSource: AlbumsDataSource,mapper: AlbumMapper) : IAlbumsRepository{
        return AlbumsRepositoryImp(api,dataSource,mapper)
    }
}

