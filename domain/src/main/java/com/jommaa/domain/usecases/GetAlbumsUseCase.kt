package com.jommaa.domain.usecases


import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.repositories.IAlbumsRepository
import javax.inject.Inject


class GetAlbumsUseCase @Inject constructor(private val albumRepository: IAlbumsRepository) {

    fun invoke(): LiveData<DataResult> = liveData {
        emit(DataResult.Loading())

        try {
            emit(DataResult.Success(albumRepository.getAlbumsList()))
        } catch (ex: Exception) {
            emit(DataResult.Failure(ex))
        }
    }
}