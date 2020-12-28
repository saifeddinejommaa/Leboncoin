package com.jommaa.data

import com.jommaa.data.response.AlbumResp
import com.jommaa.domain.model.Album
import io.reactivex.Single
import retrofit2.http.GET

interface EndPoint {
    @GET("/img/shared/technical-test.json")
    fun getAlbumsList(): Single<List<AlbumResp>>
}