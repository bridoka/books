package com.ciandt.book.seeker.presentation.search

import com.ciandt.book.seeker.data.repository.search.SearchRepository
import com.ciandt.book.seeker.data.store.model.LastSearchStoreModel
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SearchInteractorTest {

    @Mock
    private lateinit var repository: SearchRepository

    private lateinit var interactor: SearchInteractor

    @Before
    fun setup() {
        interactor = SearchInteractor(repository)
    }

    @Test
    fun shouldGetLastSearch() {
        whenever(repository.getLastSearches()).thenReturn(
            Flowable.just(
                listOf(
                    LastSearchStoreModel(
                        id = 1,
                        description = "Cook Book"
                    )
                )
            )
        )

        val result = interactor.getLastSearches().blockingFirst()
        verify(repository, times(1)).getLastSearches()
        assertEquals(getListViewEntity(), result)
    }

    private fun getListViewEntity(): List<LastSearchViewEntity> {
        return listOf(
            LastSearchViewEntity(
                id = 1,
                description = "Cook Book"
            )
        )
    }

    @Test
    fun shouldSaveLastSearch() {
        whenever(
            repository.saveLastSearch(
                LastSearchStoreModel(
                    id = null,
                    description = "abc"
                )
            )
        ).thenReturn(Completable.complete())

        interactor.saveLastSearch("abc").test()
        verify(repository, times(1)).saveLastSearch(
            LastSearchStoreModel(
                id = null,
                description = "abc"
            )
        )
    }
}