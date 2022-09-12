package com.jommaa.data.repositoriesImp

import com.jommaa.data.dataSource.remote.AlbumsApi
import com.jommaa.data.mappers.AlbumMapper
import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.IAlbumsRepository
import javax.inject.Inject


class AlbumsRepositoryImp @Inject constructor(
    private val api: AlbumsApi,
    private val mapper: AlbumMapper
) : IAlbumsRepository {
    override suspend fun getAlbumsList(): List<Album> {
        return api.getAlbumsList().map { mapper.albumDTOtoAlbum(it) }
    }
}