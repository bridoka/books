package com.ciandt.book.seeker.presentation.search

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchViewModel @ViewModelInject constructor(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

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
            }.addTo(this.compositeDisposable)
    }

    private fun getLastSearches() {
        searchInteractor
            .getLastSearches()
            .subscribeOn(Schedulers.io())
            .subscribe({
                lastSearches.set(it)
            }, {
                Timber.w("Error")
            }).addTo(this.compositeDisposable)
    }

    fun onClickSearch() {
        _onClickButton.postValue(Unit)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}