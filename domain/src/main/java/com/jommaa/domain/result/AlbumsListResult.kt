package com.jommaa.domain.result

import com.jommaa.domain.model.Album
import io.reactivex.Single

sealed class AlbumsListResult {
    object Loading : AlbumsListResult()
    data class Success(val albums: List<Album>) : AlbumsListResult()
    data class Failure(val throwable: Throwable) : AlbumsListResult()
}