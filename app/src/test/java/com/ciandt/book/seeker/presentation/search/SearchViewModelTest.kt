package com.ciandt.book.seeker.presentation.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.eq
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: SearchInteractor

    @Mock
    private lateinit var onClickButtonEventObserver: Observer<String>

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        whenever(interactor.getLastSearches()).thenReturn(
            Flowable.just(
                listOf(
                    LastSearchViewEntity(
                        id = 1,
                        description = "Cook Book"
                    )
                )
            )
        )
        viewModel = SearchViewModel(interactor)
    }

    @Test
    fun shouldExecuteSearch() {
        whenever(interactor.saveLastSearch("abc")).thenReturn(Completable.complete())

        viewModel.searchText.set("abc")
        viewModel.onClickButton.observeForever(onClickButtonEventObserver)
        viewModel.onClickSearch()

        verify(interactor, times(1)).saveLastSearch("abc")
        verify(onClickButtonEventObserver).onChanged(eq("abc"))
        verify(interactor, times(1)).saveLastSearch("abc")
    }
}