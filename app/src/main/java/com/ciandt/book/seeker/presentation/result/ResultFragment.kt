package com.ciandt.book.seeker.presentation.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciandt.book.seeker.R
import com.ciandt.book.seeker.databinding.ResultFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates


@AndroidEntryPoint
class ResultFragment : Fragment() {

    private val viewModel: ResultViewModel by viewModels()

    private val args: ResultFragmentArgs by navArgs()

    private val bookListAdapter: BookListAdapter by lazy {
        BookListAdapter()
    }

    private var binding: ResultFragmentBinding by Delegates.notNull()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.result_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchText = args.searchText
        viewModel.getBooks(searchText)
        setUpRecyclerView()
        bookListAdapter.clickItemEvent.observe(viewLifecycleOwner, {
            val action =
                ResultFragmentDirections.actionResultFragmentToDetailsFragment(it, args.searchText)
            view.findNavController().navigate(action)
        })
    }

    private fun setUpRecyclerView() {
        binding.recyclerviewBookList.apply {
            layoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically() = false
            }
            adapter = bookListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerviewBookList.adapter = null
    }
}