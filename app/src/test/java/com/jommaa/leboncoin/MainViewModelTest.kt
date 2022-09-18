package com.jommaa.leboncoin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.entities.Album
import com.jommaa.domain.usecases.GetAlbumsUseCase
import com.jommaa.leboncoin.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    @Mock
    lateinit var useCase:GetAlbumsUseCase
    private lateinit var mainViewModel:MainViewModel
    @Mock
    private lateinit var albumsObserver: Observer<in DataResult>

    @Before
    fun setUp(){
        mainViewModel = MainViewModel(useCase)
    }

    @Test
    fun `when fetching results ok then return a list successfully`(){
        val emptyList = arrayListOf<Album>()
          mainViewModel.getAlbums().observeForever(albumsObserver)
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(useCase.invoke()).thenAnswer {
                DataResult.Success(emptyList)
            }
            mainViewModel.fetchAlbums()
            assertNotNull(mainViewModel.getAlbums().value)

            assertEquals(0, (mainViewModel.getAlbums().value as DataResult.Success).albums.size)
        }
    }

    @Test
    fun `when calling for results then return loading`() {
        testCoroutineRule.runBlockingTest {
            mainViewModel.getAlbums().observeForever(albumsObserver)
            mainViewModel.fetchAlbums()
            Mockito.verify(albumsObserver).onChanged(DataResult.Loading)
        }
    }

    @Test
    fun `when fetching results fails then return an error`() {
        val exception = Mockito.mock(HttpException::class.java)
        testCoroutineRule.runBlockingTest {
            mainViewModel.getAlbums().observeForever(albumsObserver)
            Mockito.`when`(
                useCase.invoke()
            ).thenAnswer {
               DataResult.Failure(UnknownHostException(),"")
            }
            mainViewModel.fetchAlbums()
            assertNotNull(mainViewModel.getAlbums().value)
        }
    }
}