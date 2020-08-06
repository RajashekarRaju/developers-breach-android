package com.developerbreach.developerbreach.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.developerbreach.developerbreach.databinding.FragmentSearchBinding
import java.util.concurrent.TimeUnit


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Assign and get class ViewModel and pass fragment owner and factory to create instance
        // by calling ViewModelProviders.
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Get reference to binding and inflate this class layout.
        binding = FragmentSearchBinding.inflate(inflater)
        // Time taken for fragment to enter with transition
        postponeEnterTransition(100L, TimeUnit.MILLISECONDS)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.toolbarContentSearchHeader.viewModel = viewModel
        binding.toolbarContentSearchHeader.fragment = this
        binding.toolbarContentSearchHeader.recyclerView = binding.searchRecyclerView
        binding.toolbarContentSearchHeader.searchErrorText = binding.noSearchResultsFoundText
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment.
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        // Return root binding view.
        return binding.root
    }
}