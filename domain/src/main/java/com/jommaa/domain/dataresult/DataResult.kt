package com.jommaa.domain.dataresult

import com.jommaa.domain.entities.Album
import java.lang.Exception

sealed class DataResult {
     class Success(albums:List<Album>) : DataResult()
     class Loading() : DataResult()
     class Error(exception: Exception) : DataResult()
}