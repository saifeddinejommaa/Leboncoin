package com.jommaa.domain.usecases


import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.IAlbumsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class GetAlbumsUseCase @Inject constructor(private val albumRepository: IAlbumsRepository) {

    /**
     * Call Album Api to retrieve the albums list.
     * If Album Api returns a not empty list, all albums returned will be saved in local data Base
     * If Album Api return an exception, this method call the local db for getting the albums list that is saved in the last call
     * If Album Api return an exception and no data in local data Base, this method return an Exception with "No available data" message
     */
    suspend fun invoke(): DataResult {
        try {
            val albumsList = albumRepository.getAlbumsListFromServer()
            when (isNotEmpty(albumsList)) {
                true -> {
                    insertAlbumsInDB(albumsList)
                    return DataResult.Success(albumsList)
                }
            }
        } catch (ex: Exception) {
            val localAlbums = getAlbumsListFromDB()
            return when (isNotEmpty(localAlbums)) {
                true -> DataResult.Success(localAlbums)
                else -> DataResult.Failure(
                    ex,
                    "No data available to display, please make sure you have internet connection and try again"
                )
            }
        }
        return DataResult.Failure(Exception("Unknown Error"), "Unknown Error, please try again")

    }

    /**
     * get all albums from local DB
     */
    private suspend fun getAlbumsListFromDB(): List<Album> {
        val albums = mutableListOf<Album>()
        GlobalScope.launch(Dispatchers.IO) {
            albums.addAll(albumRepository.getAlbumsListFromDB())
        }
        return albums
    }

    /**
     * Insert into local DB an albums list
     * @param:albumsList : albums list that will be saved
     **/
    private suspend fun insertAlbumsInDB(albumsList: List<Album>) {
        GlobalScope.launch(Dispatchers.IO) {
            albumRepository.putAlbumsInDB(albumsList)
        }
    }

    /*
     * check if a list is not null or empty
     * @param: list to check
     */
    private fun isNotEmpty(list: List<*>?): Boolean {
        return list != null && list.isNotEmpty()
    }

}