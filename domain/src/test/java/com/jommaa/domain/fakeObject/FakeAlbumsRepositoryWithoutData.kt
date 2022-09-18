package com.jommaa.domain.fakeObject

import com.jommaa.domain.entities.Album
import com.jommaa.domain.repositories.IAlbumsRepository
import java.util.*

class FakeAlbumsRepositoryWithoutData: IAlbumsRepository {

    override suspend fun getAlbumsListFromServer(): List<Album> {
        return Arrays.asList()
    }

    override suspend fun getAlbumsListFromDB(): List<Album> {
        return Arrays.asList()
    }

    override suspend fun putAlbumsInDB(albums: List<Album>) {
        TODO("Not yet implemented")
    }
}