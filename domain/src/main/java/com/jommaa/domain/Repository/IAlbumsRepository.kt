package com.jommaa.domain.Repository

import com.jommaa.domain.model.Album
import io.reactivex.Single

interface IAlbumsRepository {

    fun getAlbumsList():Single<List<Album>>

    fun getLocalAlbumsList():List<Album>

    fun putAllAlbums(list:List<Album>)

    fun isDbEmpty() : Boolean
}