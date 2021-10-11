package com.ciandt.book.seeker.presentation.result

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ciandt.book.seeker.BR
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.databinding.RowBookBinding
import com.ciandt.book.seeker.utils.SingleLiveEvent


class BookListAdapter :
    RecyclerView.Adapter<BookListAdapter.DataBindViewHolder<RowBookBinding>>() {

    private var items: List<BookListViewEntity> = arrayListOf()

    val clickItemEvent: LiveData<String> get() = _clickItemEvent
    private val _clickItemEvent = SingleLiveEvent<String>()

    class DataBindViewHolder<out T : ViewDataBinding>(val binding: T) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindViewHolder<RowBookBinding> =
        DataBindViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_book,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataBindViewHolder<RowBookBinding>, position: Int) {
        holder.binding.setVariable(BR.viewEntity, items[position])
        holder.binding.rowBook.setOnClickListener {
            _clickItemEvent.postValue(items[position].urlPreview)
        }
    }

    override fun getItemCount() = items.size

    private fun update(list: List<BookListViewEntity>) {
        items = list as? List<BookListViewEntity> ?: arrayListOf()
        notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("resultListBooks")
        fun setResultListBooks(
            recyclerView: RecyclerView,
            books: List<BookListViewEntity>?
        ) = books?.let {
            (recyclerView.adapter as? BookListAdapter)?.update(it)
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImage(
            view: ImageView,
            url: String?
        ) = url?.let {
            Glide.with(view.context)
                .load(url)
                .into(view)
        }
    }
}