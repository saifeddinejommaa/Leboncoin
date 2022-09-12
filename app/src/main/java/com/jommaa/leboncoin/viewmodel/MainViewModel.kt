package com.jommaa.leboncoin.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.usecases.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val useCase: GetAlbumsUseCase) : ViewModel() {

    private var albums: LiveData<DataResult> = useCase.invoke()

    fun getAlbums(): LiveData<DataResult>? {
        return albums
    }


}