package com.jommaa.data.dto.repositoriesImp

import com.jommaa.data.dto.dataSource.remote.AlbumsApi
import com.jommaa.data.dto.mappers.AlbumMapper
import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.AlbumsRepository
import javax.inject.Inject


class AlbumsRepositoryImp @Inject constructor(private val api:AlbumsApi,private val mapper: AlbumMapper): AlbumsRepository {
    override suspend fun getAlbumsList(): List<Album> {
        return api.getAlbumsList().map { mapper.AlbumDTOtoAlbum(it) }
    }
}