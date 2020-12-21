package com.jommaa.data.db.datasource

import android.app.Application
import com.jommaa.domain.model.Album
import com.jommaa.data.db.AlbumDB
import io.reactivex.Completable

class AlbumsDataSource (context: Application) {
    val dataBase=AlbumDB.invoke(context)

    fun insertAlbum(town: Album?)  {
        return dataBase.AlbumsDao().insert(town)
    }

    fun getAllAlbums(): List<Album> {
        return  dataBase.AlbumsDao().getAll()
    }

    fun getAlbumById(id : Int): List<Album> {
        return  dataBase.AlbumsDao().loadById(id)
    }

    fun getAlbumsCount() : Int {
        return dataBase.AlbumsDao().getAlbumsCount()
    }


}