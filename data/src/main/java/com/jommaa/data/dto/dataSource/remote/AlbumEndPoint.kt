package com.jommaa.data.dto.dataSource.remote

import com.jommaa.data.dto.AlbumDto
import retrofit2.http.GET

interface AlbumEndPoint {
    @GET
     fun getAlbumsList(): List<AlbumDto>
}