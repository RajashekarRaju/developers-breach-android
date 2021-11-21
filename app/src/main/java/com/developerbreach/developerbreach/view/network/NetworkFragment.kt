package com.developerbreach.developerbreach.view.network

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.developerbreach.developerbreach.databinding.FragmentNetworkBinding


class NetworkFragment : Fragment() {

    private lateinit var binding: FragmentNetworkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNetworkBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = NetworkManager(requireContext()).isConnected
        manager.observe(viewLifecycleOwner, { connected ->
            if (connected) {
                findNavController().navigateUp()
            }
        })

        binding.noInternetConnectionTryAgain.setOnClickListener {
            // checkNetworkConnection()
        }
    }
}