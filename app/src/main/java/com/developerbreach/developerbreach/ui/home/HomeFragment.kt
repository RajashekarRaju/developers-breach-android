package com.developerbreach.developerbreach.ui.home

//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import com.developerbreach.developerbreach.controller.AppNavDirections
//import com.developerbreach.developerbreach.databinding.FragmentHomeBinding
//import com.developerbreach.developerbreach.utils.PrefUtils
//import com.developerbreach.developerbreach.utils.RecyclerViewItemDecoration.Companion.setItemSpacing
//import java.util.concurrent.TimeUnit
//
//
//class HomeFragment : Fragment() {
//
//    private lateinit var binding: FragmentHomeBinding
//    private val viewModel: HomeViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val isStateChanged = PrefUtils(requireContext()).checkPreferenceSaveState()
//        if (!isStateChanged) {
//            AppNavDirections(findNavController()).homeToIntro()
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentHomeBinding.inflate(inflater, container, false)
//        setItemSpacing(resources, binding.recentPostsRecyclerView)
//        // Time taken for fragment to enter with transition
//        postponeEnterTransition(100L, TimeUnit.MILLISECONDS)
//        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment.
//        binding.lifecycleOwner = this
//        binding.viewModel = viewModel
//        binding.executePendingBindings()
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.searchContainerCard.setOnClickListener {
//            AppNavDirections(findNavController()).homeToSearch()
//        }
//
//        binding.expandBottomAppbarImageView.setOnClickListener {
//            AppNavDirections(findNavController()).homeToOptions()
//        }
//
//        binding.bottomAppBar.setOnClickListener {
//            AppNavDirections(findNavController()).homeToOptions()
//        }
//
//        binding.viewAllPostsTextView.setOnClickListener {
//            AppNavDirections(findNavController()).homeToArticleList()
//        }
//    }
//}