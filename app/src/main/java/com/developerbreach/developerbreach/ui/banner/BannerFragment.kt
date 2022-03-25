package com.developerbreach.developerbreach.ui.banner
//
//import android.graphics.Color
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.fragment.findNavController
//import com.developerbreach.developerbreach.databinding.FragmentBannerBinding
//import com.google.android.material.transition.MaterialContainerTransform
//
//
//class BannerFragment : Fragment() {
//
//    private lateinit var viewModel: BannerViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val bannerUrlLink = BannerFragmentArgs.fromBundle(requireArguments()).bannerUrlLink
//        val factory = BannerViewModelFactory(requireActivity().application, bannerUrlLink)
//        viewModel = ViewModelProvider(this, factory)[BannerViewModel::class.java]
//
//        sharedElementEnterTransition = MaterialContainerTransform().apply {
//            this.duration = 300L
//            this.containerColor = Color.BLACK
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment
//        val binding = FragmentBannerBinding.inflate(inflater, container, false)
//        binding.lifecycleOwner = this
//        binding.viewModel = viewModel
//        binding.navController = findNavController()
//        binding.executePendingBindings()
//        return binding.root
//    }
//}