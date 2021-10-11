package com.ciandt.book.seeker.presentation.search

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciandt.book.seeker.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    val lastSearches = ObservableField<List<LastSearchViewEntity>>()

    private var _onClickButton = SingleLiveEvent<Unit>()
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