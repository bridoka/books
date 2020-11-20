package com.ciandt.book.seeker.presentation.result

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ciandt.book.seeker.BR
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.databinding.RowBookBinding

class BookListAdapter : RecyclerView.Adapter<BookListAdapter.DataBindViewHolder>() {

    private var items: List<BookListViewEntity> = arrayListOf()

    class DataBindViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindViewHolder =
        DataBindViewHolder(
            DataBindingUtil.inflate<RowBookBinding>(
                LayoutInflater.from(parent.context),
                R.layout.row_book,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataBindViewHolder, position: Int) {
        holder.binding.setVariable(BR.viewEntity, items[position])
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
                .into(view);
        }
    }
}