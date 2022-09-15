package com.jommaa.data.repositories

import com.jommaa.data.dataSource.local.datsource.AlbumsDataSource
import com.jommaa.data.dataSource.remote.AlbumEndPoint
import com.jommaa.data.dataSource.remote.AlbumsApi
import com.jommaa.data.mockObjects.MockObjects
import io.mockk.coEvery
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class AlbumsRepositoryTest {

    lateinit var albumsApi: AlbumsApi
    lateinit var dataSource: AlbumsDataSource

    @Before
    fun setUp() = runBlocking {
        {
            val albumEndPoint = Mockito.mock(AlbumEndPoint::class.java)
           // val dataSource
            albumsApi = AlbumsApi(albumEndPoint)
            coEvery {
                Mockito.`when`(albumEndPoint.getAlbumsList())
                    .thenReturn(MockObjects.fakeAlbumsDtoList())
            }
        }
    }
}