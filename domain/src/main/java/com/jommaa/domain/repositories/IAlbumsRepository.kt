package com.jommaa.domain.repositories

import com.jommaa.domain.entities.Album

interface IAlbumsRepository {

    suspend fun getAlbumsList(): List<Album>
}