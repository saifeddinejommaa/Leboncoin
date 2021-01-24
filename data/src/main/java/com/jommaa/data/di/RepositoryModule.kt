package com.jommaa.data.di
import com.jommaa.data.repositoryImp.AlbumsRepositoryImp
import com.jommaa.domain.Repository.IAlbumsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class  RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAlbumsRepository(albumsRepositoryImp: AlbumsRepositoryImp): IAlbumsRepository
}

