package com.ciandt.book.seeker.presentation.search

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchViewModel @ViewModelInject constructor(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    val lastSearches = ObservableField<List<LastSearchViewEntity>>()

    private var _onClickButton = MutableLiveData<Unit>()
    val onClickButton: LiveData<Unit>
        get() = _onClickButton

    init {
        getLastSearches()
    }

    fun searchBooks(search: Flowable<String>) {
        search
            .doOnComplete {
                getLastSearches()
            }
            .subscribeOn(Schedulers.io())
            .subscribe {
                it?.let {
                    searchInteractor.saveLastSearch(it)
                }
            }
    }

    private fun getLastSearches() {
        searchInteractor
            .getLastSearches()
            .subscribeOn(Schedulers.io())
            .subscribe({
                lastSearches.set(it)
            }, {
                Timber.w("Error")
            })
    }

    fun onClickSearch() {
        _onClickButton.postValue(Unit)
    }
}