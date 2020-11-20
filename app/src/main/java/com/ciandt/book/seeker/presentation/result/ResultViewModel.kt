package com.ciandt.book.seeker.presentation.result

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ResultViewModel @ViewModelInject constructor(
    val resultInteractor: ResultInteractor
) : ViewModel() {

    val bookList = ObservableField<List<BookListViewEntity>>()

    fun getBooks(searchText: String?) {
        resultInteractor.getBooksList(searchText)
            .subscribeOn(Schedulers.io())
            .subscribe({
                bookList.set(it)
            }, {
                Timber.w("Error")
            })
    }
}