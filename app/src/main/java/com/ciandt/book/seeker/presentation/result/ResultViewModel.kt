package com.ciandt.book.seeker.presentation.result

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ResultViewModel @ViewModelInject constructor(
    private val resultInteractor: ResultInteractor
) : ViewModel() {

    var compositeDisposable = CompositeDisposable()

    val bookList = ObservableField<List<BookListViewEntity>>()

    fun getBooks(searchText: String?) {
        resultInteractor.getBooksList(searchText)
            .subscribeOn(Schedulers.io())
            .subscribe({
                bookList.set(it)
            }, {
                Timber.w("Error")
            }).addTo(this.compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}