package com.ciandt.book.seeker.presentation.search

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ciandt.book.seeker.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
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

    var searchText = ObservableField<String>()

    private var _onClickButton = SingleLiveEvent<String>()
    val onClickButton: LiveData<String>
        get() = _onClickButton

    init {
        getLastSearches()
    }

    private fun saveTerm() {
        searchText.get()?.let {
            searchInteractor.saveLastSearch(it)
                .subscribeOn(Schedulers.io())
                .subscribe()
                .addTo(this.compositeDisposable)
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
            .addTo(this.compositeDisposable)
    }

    fun onClickSearch() {
        saveTerm()
        _onClickButton.postValue(searchText.get())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}