package com.ciandt.book.seeker.presentation.result

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val resultInteractor: ResultInteractor
) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    val shimmerVisibility = ObservableInt(View.VISIBLE)
    val listBooksVisibility = ObservableInt(View.GONE)

    val bookList = ObservableField<List<BookListViewEntity>>()

    fun getBooks(searchText: String?) {
        resultInteractor.getBooksList(searchText)
            .subscribeOn(Schedulers.io())
            .subscribe({
                bookList.set(it)
                shimmerVisibility.set(View.GONE)
                listBooksVisibility.set(View.VISIBLE)
            }, {
                Timber.w(it, "Error")
            }).addTo(this.compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}