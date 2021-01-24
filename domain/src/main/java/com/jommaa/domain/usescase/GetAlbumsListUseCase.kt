package com.jommaa.domain.usescase

import com.jommaa.domain.Repository.IAlbumsRepository
import com.jommaa.domain.model.Album
import com.jommaa.domain.result.AlbumsListResult
import com.jommaa.domain.result.AlbumsListResult.*
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import sun.rmi.runtime.Log
import javax.inject.Inject
import kotlin.concurrent.thread

class GetAlbumsListUseCase @Inject constructor(val repo: IAlbumsRepository){

    fun execute(): Observable<AlbumsListResult>{
     return   repo.getAlbumsList()
            .toObservable()
            .map { onSuccessGettingAlbums(it) }
            .onErrorReturn { onFailureGettingAlbums(it) }
            .startWith(Loading)
    }

   private fun onSuccessGettingAlbums(albums:List<Album>) : AlbumsListResult{
      repo.putAllAlbums(albums).subscribeOn(Schedulers.io())
          .subscribe({Log.getLog("Success","",false)},{})
       return Success(albums)
   }

    private fun onFailureGettingAlbums(throwable: Throwable):AlbumsListResult{
        if(!repo.isDbEmpty())
          return Success(repo.getLocalAlbumsList())
        return Failure(throwable)
    }

}