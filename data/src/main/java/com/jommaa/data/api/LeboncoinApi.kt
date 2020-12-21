package com.jommaa.data.api
import com.jommaa.data.EndPoint
import com.jommaa.domain.model.Album
import io.reactivex.Single
import javax.inject.Inject

class LeboncoinApi @Inject constructor (private val endPoint: EndPoint) {

    fun getAlbumsList(): Single<List<Album>> {
        return endPoint.getAlbumsList()
    }
}