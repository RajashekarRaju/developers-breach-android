package com.developerbreach.developerbreach.view.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.databinding.FragmentHomeBinding
import com.developerbreach.developerbreach.utils.RecyclerViewItemDecoration.Companion.setItemSpacing
import com.developerbreach.developerbreach.utils.showSnackBar
import com.developerbreach.developerbreach.controller.AppNavDirections
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences(
            getString(R.string.preference_intro_result_key), Context.MODE_PRIVATE
        )

        with(
            sharedPref.getString(
                getString(R.string.preference_intro_status_key),
                getString(R.string.preference_intro_fragment_not_shown_value)
            )
        ) {
            if (!this.equals(getString(R.string.preference_intro_fragment_shown_value))) {
                AppNavDirections(findNavController()).homeToIntro()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setItemSpacing(resources, binding.recentPostsRecyclerView)
        // Time taken for fragment to enter with transition
        postponeEnterTransition(100L, TimeUnit.MILLISECONDS)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment.
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isInternetAvailable.observe(viewLifecycleOwner, { isInternetAvailable ->
            if (!isInternetAvailable) {
                showSnackBar(getString(R.string.no_internet_connection), requireActivity())
            }
        })

        viewModel.articles.observe(viewLifecycleOwner, { articles ->
            if (articles.isNotEmpty()) {
                binding.recentPostsRecyclerView.visibility = View.VISIBLE
                binding.includeBrandLayout.root.visibility = View.GONE
            } else {
                binding.recentPostsRecyclerView.visibility = View.GONE
                binding.includeBrandLayout.root.visibility = View.VISIBLE
            }
        })

        binding.searchContainerCard.setOnClickListener {
            AppNavDirections(findNavController()).homeToSearch()
        }

        binding.expandBottomAppbarImageView.setOnClickListener {
            AppNavDirections(findNavController()).homeToOptions()
        }
    }
}