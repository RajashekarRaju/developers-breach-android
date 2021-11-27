package com.developerbreach.developerbreach.view.commonWebView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.developerbreach.developerbreach.databinding.FragmentCommonWebViewBinding


class CommonWebViewFragment : Fragment() {

    private lateinit var viewModel: CommonWebViewViewModel
    private val args: CommonWebViewFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = CommonWebViewViewModelFactory(
            requireActivity().application,
            args.webViewFragmentArgs
        )
        viewModel = ViewModelProvider(this, factory)[CommonWebViewViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentCommonWebViewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.navController = findNavController()
        binding.executePendingBindings()
        return binding.root
    }
}