package com.ciandt.book.seeker.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Flowable
import kotlinx.android.synthetic.main.search_fragment.view.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels()

    private val lastSearchAdapter: LastSearchAdapter by lazy {
        LastSearchAdapter()
    }

    private var binding: SearchFragmentBinding by Delegates.notNull()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.search_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        viewModel.onClickButton.observe(viewLifecycleOwner, {
            val editText = view.edit_search_book.text.toString()
            viewModel.searchBooks(Flowable.just(editText))
            val action = SearchFragmentDirections.actionSearchFragmentToResultFragment(editText)
            view.findNavController().navigate(action)
        })

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun setUpRecyclerView() {
        binding.recyclerviewLastSearch.apply {
            layoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically() = false
            }
            adapter = lastSearchAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerviewLastSearch.adapter = null
    }
}