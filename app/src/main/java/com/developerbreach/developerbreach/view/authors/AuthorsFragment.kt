package com.developerbreach.developerbreach.view.authors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.developerbreach.developerbreach.databinding.FragmentAuthorsBinding
import com.developerbreach.developerbreach.utils.RecyclerViewItemDecoration
import java.util.concurrent.TimeUnit

class AuthorsFragment : Fragment() {

    private lateinit var binding: FragmentAuthorsBinding
    private val viewModel: AuthorsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorsBinding.inflate(inflater, container, false)
        RecyclerViewItemDecoration.setItemSpacing(resources, binding.authorsRecyclerView)
        // Time taken for fragment to enter with transition
        postponeEnterTransition(100L, TimeUnit.MILLISECONDS)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment.
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.authors.observe(viewLifecycleOwner, { authors ->
            val adapter = AuthorsAdapter(viewModel)
            adapter.submitList(authors)
            binding.authorsRecyclerView.adapter = adapter
        })
    }
}