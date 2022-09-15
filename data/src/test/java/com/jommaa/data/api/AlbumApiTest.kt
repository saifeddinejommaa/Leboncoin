package com.jommaa.data.api

import com.jommaa.data.dataSource.remote.AlbumEndPoint
import com.jommaa.data.dataSource.remote.AlbumsApi
import com.jommaa.data.dto.AlbumDto
import com.jommaa.data.mockObjects.MockObjects
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import java.util.*

@RunWith(JUnit4::class)
class AlbumApiTest {

    lateinit var albumsApi: AlbumsApi
    @MockK
    lateinit var albumEndPoint: AlbumEndPoint

    @Before
    fun setUp()
        {
            MockKAnnotations.init(this)
            albumsApi = AlbumsApi(albumEndPoint)
        }

/*
    @Test
    fun `Assert the albums returned by the api is the same list returned by Api object`(){

        coEvery { albumEndPoint.getAlbumsList()} returns MockObjects.fakeAlbumsDtoList()

        var albumResp:List<AlbumDto> = Arrays.asList()
        coVerify {
            albumResp = albumsApi.getAlbumsList()
            Assert.assertEquals(albumResp.size, MockObjects.fakeAlbumsDtoList().size)
            Assert.assertEquals(albumResp.get(0).id, MockObjects.fakeAlbumsDtoList().get(0).id)
        }

    }
    */

}