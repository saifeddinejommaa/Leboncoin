package com.jommaa.leboncoin.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.usecases.GetAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val useCase: GetAlbumsUseCase) : ViewModel() {


    private val albums = MutableLiveData<DataResult>()

    fun getAlbums(): MutableLiveData<DataResult> {
        return albums
    }

    /**
     * Start getting data
     */
    fun fetchAlbums() {
        viewModelScope.launch {
            albums.postValue(DataResult.Loading())
            albums.postValue(useCase.invoke())
        }
    }

}