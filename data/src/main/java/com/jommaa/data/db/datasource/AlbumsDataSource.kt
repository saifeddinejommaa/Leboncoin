package com.jommaa.data.db.datasource

import android.app.Application
import com.jommaa.domain.model.Album
import com.jommaa.data.db.AlbumDB
import com.jommaa.data.response.AlbumResp
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Completable
import javax.inject.Inject

class AlbumsDataSource @Inject constructor (@ApplicationContext private val context: Application) {
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