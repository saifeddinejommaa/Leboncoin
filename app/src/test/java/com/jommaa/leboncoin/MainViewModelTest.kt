package com.jommaa.leboncoin

import com.domain.rx.RxJavaTestHooksResetRule
import com.jommaa.domain.model.Album
import com.jommaa.domain.result.AlbumsListResult
import com.jommaa.domain.usescase.GetAlbumsListUseCase
import com.jommaa.leboncoin.viewmodel.MainViewModel
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

    @Mock
    lateinit var getAlbumsListUseCase: GetAlbumsListUseCase
    private lateinit var sut: MainViewModel

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        sut = MainViewModel(getAlbumsListUseCase)
    }

    @Test
    fun `bound retrieves the albums list`() {
        given(getAlbumsListUseCase.execute())
            .willReturn(Observable.just(mock()))

        sut.getAlbumsList()
        verify(getAlbumsListUseCase).execute()
    }

    @Test
    fun `bound shows error when retrieving the albums fail`() {
        given(getAlbumsListUseCase.execute())
            .willReturn(Observable.just(AlbumsListResult.Failure(Throwable())))

        sut.getAlbumsList()
    }

    @Test
    fun `bound adds albums to list when successful`() {
        val albums = arrayListOf<Album>(mock(), mock())
        given(getAlbumsListUseCase.execute())
            .willReturn(Observable.just(AlbumsListResult.Success(albums)))

        sut.getAlbumsList()

        MatcherAssert.assertThat(sut.albumsList.size, CoreMatchers.equalTo(2))
    }

    @Test
    fun `unbound clears disposables`() {
        val albums = arrayListOf<Album>(mock(), mock())
        given(getAlbumsListUseCase.execute())
            .willReturn(Observable.just(AlbumsListResult.Success(albums)))

        sut.getAlbumsList()
        MatcherAssert.assertThat(sut.disposables.size(), CoreMatchers.equalTo(1))

        sut.unbound()
        MatcherAssert.assertThat(sut.disposables.size(), CoreMatchers.equalTo(0))
    }
}