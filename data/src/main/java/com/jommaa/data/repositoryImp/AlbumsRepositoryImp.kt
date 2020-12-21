package com.jommaa.data.repositoryImp

import android.util.Log
import com.jommaa.data.api.LeboncoinApi
import com.jommaa.data.db.datasource.AlbumsDataSource
import com.jommaa.domain.Repository.IAlbumsRepository
import com.jommaa.domain.model.Album
import com.jommaa.domain.result.AlbumsListResult
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlbumsRepositoryImp(private val api:LeboncoinApi,private val dataSource: AlbumsDataSource) : IAlbumsRepository {

    private val TAG = AlbumsRepositoryImp::class.qualifiedName

    override fun getAlbumsList(): Single<List<Album>> {
       return api.getAlbumsList()
    }

    override fun getLocalAlbumsList(): List<Album> {
       return dataSource.getAllAlbums()
    }

    override fun putAllAlbums(list: List<Album>) {
        for(album in list){
           dataSource.insertAlbum(album)
        }
    }

    override fun isDbEmpty(): Boolean {
       return dataSource.getAlbumsCount()==0
    }

}