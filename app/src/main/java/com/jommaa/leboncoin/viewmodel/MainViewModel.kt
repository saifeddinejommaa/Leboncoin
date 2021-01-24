package com.jommaa.leboncoin.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.jommaa.domain.model.Album
import com.jommaa.domain.result.AlbumsListResult
import com.jommaa.domain.usescase.GetAlbumsListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class MainViewModel @Inject constructor(val getAlbumListUseCase : GetAlbumsListUseCase) : ViewModel() {

    val albumsList = ObservableArrayList<Album>()
    val disposables = CompositeDisposable()
    var  progressVisible = ObservableBoolean()
    var emptyMessageVisible = ObservableBoolean()
    fun getAlbumsList()  {
        getAlbumListUseCase.execute().
            subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleAlbumListReception(it) }
            .addTo(disposables)
    }

    fun unbound() {
        disposables.clear()
    }

    fun clear(){
        disposables.clear()
    }

    private fun handleAlbumListReception(result:AlbumsListResult) {
      progressVisible.set( true)
        emptyMessageVisible.set(false)
      when (result){
         is AlbumsListResult.Success ->{
             albumsList.addAll(result.albums)
             emptyMessageVisible.set( false)
             progressVisible.set(false)
          }
          is AlbumsListResult.Failure -> {
              emptyMessageVisible.set( true)
              progressVisible.set(false)
          }

      }
    }



}