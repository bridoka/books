package com.ciandt.book.seeker.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ciandt.book.seeker.BR
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.databinding.RowLastSearchBinding

class LastSearchAdapter : RecyclerView.Adapter<LastSearchAdapter.DataBindViewHolder>() {

    private var items: List<LastSearchViewEntity> = arrayListOf()

    class DataBindViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindViewHolder =
        DataBindViewHolder(
            DataBindingUtil.inflate<RowLastSearchBinding>(
                LayoutInflater.from(parent.context),
                R.layout.row_last_search,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataBindViewHolder, position: Int) {
        holder.binding.setVariable(BR.viewEntity, items[position])
    }

    override fun getItemCount() = items.size

    private fun update(list: List<LastSearchViewEntity>) {
        items = list as? List<LastSearchViewEntity> ?: arrayListOf()
        notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("resultListSearch")
        fun setResultListSearch(
            recyclerView: RecyclerView,
            books: List<LastSearchViewEntity>?
        ) = books?.let {
            (recyclerView.adapter as? LastSearchAdapter)?.update(it)
        }
    }
}