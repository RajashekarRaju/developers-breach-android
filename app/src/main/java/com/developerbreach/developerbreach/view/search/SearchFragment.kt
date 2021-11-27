package com.developerbreach.developerbreach.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.developerbreach.developerbreach.databinding.FragmentSearchBinding
import java.util.concurrent.TimeUnit


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get reference to binding and inflate this class layout.
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        // Time taken for fragment to enter with transition
        postponeEnterTransition(100L, TimeUnit.MILLISECONDS)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.navController = findNavController()
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment.
        binding.executePendingBindings()
        // Return root binding view.
        return binding.root
    }
}