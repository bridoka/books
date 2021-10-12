package com.ciandt.book.seeker.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class SearchFragment : Fragment() {

    val viewModel: SearchViewModel by viewModels()

    private val lastSearchAdapter: LastSearchAdapter by lazy {
        LastSearchAdapter()
    }

    private var binding: SearchFragmentBinding by Delegates.notNull()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
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
            if (it.isNotEmpty()) {
                val action = SearchFragmentDirections.actionSearchFragmentToResultFragment(it)
                view.findNavController().navigate(action)
            }
        })
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