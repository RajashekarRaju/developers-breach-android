package com.developerbreach.developerbreach.view.banner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.developerbreach.developerbreach.databinding.FragmentBannerBinding


class BannerFragment : Fragment() {

    private lateinit var viewModel: BannerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bannerUrlLink = BannerFragmentArgs.fromBundle(requireArguments()).bannerUrlLink
        val factory = BannerViewModelFactory(requireActivity().application, bannerUrlLink)
        viewModel = ViewModelProvider(this, factory).get(BannerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentBannerBinding = FragmentBannerBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.lifecycleOwner = this
        return binding.root
    }
}