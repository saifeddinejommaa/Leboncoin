package com.jommaa.data.mapper

import com.jommaa.data.response.AlbumResp
import com.jommaa.domain.model.Album
import javax.inject.Inject

class  AlbumMapper @Inject constructor() {

    fun toAlbums(list: List<AlbumResp>) : List<Album>{
       return list.map { toAlbum(it) }
    }

    fun toAlbum(response:AlbumResp):Album{
        return Album(response.AlbumId,response.Id,response.title,response.url,response.thumbnailUrl)
    }

    fun toAlbumResp(album: Album): AlbumResp{
        return AlbumResp(album.AlbumId,album.Id,album.title,album.url,album.thumbnailUrl)
    }
}