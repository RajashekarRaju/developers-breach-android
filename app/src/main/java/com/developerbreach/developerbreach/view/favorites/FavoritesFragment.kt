package com.developerbreach.developerbreach.view.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.developerbreach.developerbreach.databinding.FragmentFavoritesBinding
import com.developerbreach.developerbreach.utils.RecyclerViewItemDecoration.Companion.setItemSpacing
import java.util.concurrent.TimeUnit

class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get reference to binding and inflate this class layout.
        val binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        // Set item decoration for items inside recycler view.
        setItemSpacing(resources, binding.favoritesRecyclerView)
        // Time taken for fragment to enter with transition
        postponeEnterTransition(300L, TimeUnit.MILLISECONDS)
        binding.lifecycleOwner = this
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment.
        binding.viewModel = viewModel
        binding.navController = findNavController()
        binding.executePendingBindings()
        // Return root binding view.
        return binding.root
    }
}
