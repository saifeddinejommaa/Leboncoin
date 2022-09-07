package com.jommaa.domain.repositories

import com.jommaa.domain.entities.Album

interface AlbumsRepository {

    suspend fun getAlbumsList(): List<Album>
}