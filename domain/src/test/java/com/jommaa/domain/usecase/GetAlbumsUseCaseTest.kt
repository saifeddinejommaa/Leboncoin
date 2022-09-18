package com.jommaa.domain.usecase

import com.jommaa.domain.dataresult.DataResult
import com.jommaa.domain.fakeObject.FakeAlbumsRepositoryWithData
import com.jommaa.domain.fakeObject.FakeAlbumsRepositoryWithoutData
import com.jommaa.domain.repositories.IAlbumsRepository
import com.jommaa.domain.usecases.GetAlbumsUseCase
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.runBlocking
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException
import org.mockito.stubbing.Answer


@RunWith(MockitoJUnitRunner::class)
class GetAlbumsUseCaseTest {

    lateinit var getAlbumsUseCase: GetAlbumsUseCase

    @Test
    fun `Assert the albums returned by the repo is the same list returned by the use case`() {
        runBlocking {
            getAlbumsUseCase = GetAlbumsUseCase(FakeAlbumsRepositoryWithData())
            val result = getAlbumsUseCase.invoke()
            assert(result is DataResult.Success)
            assert((result as DataResult.Success).albums.size == 2)
        }
    }

    @Test
    fun `Assert that use case return success when repository returns 0 albums`() {
        runBlocking {
            getAlbumsUseCase = GetAlbumsUseCase(FakeAlbumsRepositoryWithoutData())
            val result = getAlbumsUseCase.invoke()
            assert(result is DataResult.Success)
            assert((result as DataResult.Success).albums.isEmpty())
        }
    }

    @Test
    fun test () {
        runBlocking {
            val repo = Mockito.mock(IAlbumsRepository::class.java)
            BDDMockito.given(repo.getAlbumsListFromServer())
                .willAnswer(Answer<Any> { invocation: InvocationOnMock? ->
                    throw UnknownHostException(
                        "abc msg"
                    )
                })
            Mockito.`when`(repo.getAlbumsListFromDB()).thenReturn(listOf())
            getAlbumsUseCase = GetAlbumsUseCase(repo)
            val resp = getAlbumsUseCase.invoke()
            Mockito.verify(repo, Mockito.times(1)).getAlbumsListFromDB()
        }
    }
    }
