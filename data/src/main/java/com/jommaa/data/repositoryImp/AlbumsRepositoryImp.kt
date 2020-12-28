package com.jommaa.data.repositoryImp
import com.jommaa.data.api.LeboncoinApi
import com.jommaa.data.db.datasource.AlbumsDataSource
import com.jommaa.data.mapper.AlbumMapper
import com.jommaa.domain.Repository.IAlbumsRepository
import com.jommaa.domain.model.Album
import io.reactivex.Single
import javax.inject.Inject

class AlbumsRepositoryImp @Inject constructor(private val api:LeboncoinApi,private val dataSource: AlbumsDataSource, private val albumMapper: AlbumMapper) : IAlbumsRepository {

    private val TAG = AlbumsRepositoryImp::class.qualifiedName

    override fun getAlbumsList(): Single<List<Album>> {
       return api.getAlbumsList().map { albumMapper.toAlbums(it) }
    }

    override fun getLocalAlbumsList(): List<Album> {
       return dataSource.getAllAlbums().map { albumMapper.toAlbum(it) }
    }

    override fun putAllAlbums(list: List<Album>) {
        for(album in list){
           dataSource.insertAlbum(albumMapper.toAlbumResp(album))
        }
    }

    override fun isDbEmpty(): Boolean {
       return dataSource.getAlbumsCount()==0
    }

}