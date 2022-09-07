package com.jommaa.data.dto.dataSource.remote

import com.jommaa.data.dto.AlbumDto
import javax.inject.Inject

class AlbumsApi @Inject constructor(private val endPoint:AlbumEndPoint) {

    fun getAlbumsList(): List<AlbumDto>{
        return endPoint.getAlbumsList()
    }
}