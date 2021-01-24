package com.jommaa.data.db.datasource

import android.app.Application
import com.jommaa.data.db.AlbumDB
import com.jommaa.data.response.AlbumResp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AlbumsDataSource @Inject constructor (@ApplicationContext val context: android.content.Context) {
    val dataBase=AlbumDB.invoke(context)

    fun insertAlbum(album: AlbumResp?)  {
        return dataBase.AlbumsDao().insert(album)
    }

    fun getAllAlbums(): List<AlbumResp> {
        return  dataBase.AlbumsDao().getAll()
    }

    fun getAlbumById(id : Int): List<AlbumResp> {
        return  dataBase.AlbumsDao().loadById(id)
    }

    fun getAlbumsCount() : Int {
        return dataBase.AlbumsDao().getAlbumsCount()
    }


}