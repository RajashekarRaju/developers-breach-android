package com.developerbreach.developerbreach.view.commonWebView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.developerbreach.developerbreach.databinding.FragmentCommonWebViewBinding

/**
 * A simple [Fragment] subclass.
 */
class CommonWebViewFragment : Fragment() {

    private lateinit var viewModel: CommonWebViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: Bundle = requireArguments()
        val webUrl: String = CommonWebViewFragmentArgs.fromBundle(args).webViewFragmentArgs
        val factory = CommonWebViewViewModelFactory(requireActivity().application, webUrl)
        viewModel = ViewModelProvider(this, factory).get(CommonWebViewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentCommonWebViewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()
        return binding.root
    }
}