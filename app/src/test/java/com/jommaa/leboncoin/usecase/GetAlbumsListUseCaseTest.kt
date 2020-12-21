package com.domain.usecase

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.domain.rx.RxJavaTestHooksResetRule
import com.jommaa.domain.Repository.IAlbumsRepository
import com.jommaa.domain.model.Album
import com.jommaa.domain.result.AlbumsListResult
import com.jommaa.domain.usescase.GetAlbumsListUseCase

@RunWith(MockitoJUnitRunner::class)
class GetAlbumsListUseCaseTest {
  @get:Rule
  var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

  @Mock lateinit var IAlbumsRepository: IAlbumsRepository

  private lateinit var sut: GetAlbumsListUseCase

  @Before
  fun setUp() {
    sut = GetAlbumsListUseCase(IAlbumsRepository)
  }

  @Test
  fun `retrieves the albums list`() {
    given(IAlbumsRepository.getAlbumsList()).willReturn(Single.error(Throwable()))

    sut.execute().test()

    verify(IAlbumsRepository).getAlbumsList()
  }

  @Test
  fun `shows loading to start`() {
    given(IAlbumsRepository.getAlbumsList()).willReturn(Single.just(mock()))

    sut.execute().test().assertValueAt(0) { (it == AlbumsListResult.Loading) }
  }

  @Test
  fun `returns the success result when success retrieving the albums list`() {
    val albumsList = arrayListOf<Album>()
    given(IAlbumsRepository.getAlbumsList()).willReturn(Single.just(albumsList))

    sut.execute().test()
        .assertValueAt(1) { (it as AlbumsListResult.Success).albums == albumsList }
  }

  @Test
  fun `returns the failure result when error retrieving the albums list`() {
    val throwable = Throwable()
    val albumsList = arrayListOf<Album>()
    given(IAlbumsRepository.getAlbumsList()).willReturn(Single.error(throwable))
    given(IAlbumsRepository.getLocalAlbumsList()).willReturn(albumsList)

    sut.execute().test().assertValueAt(1) { (it as AlbumsListResult.Success).albums == albumsList }
  }
}