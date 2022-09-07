package com.jommaa.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.repositories.AlbumsRepository
import java.lang.Exception
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(val albumRepository:AlbumsRepository) {

   suspend fun execute():LiveData<DataResult> = liveData {
           try {
               emit(DataResult.Loading())
               val albums = albumRepository.getAlbumsList()
               emit(DataResult.Success(albums))
           }
           catch (ex:Exception){
               emit(DataResult.Error(ex))
       }
   }
}