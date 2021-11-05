package com.developerbreach.developerbreach.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.developerbreach.developerbreach.controller.AppNavDirections
import com.developerbreach.developerbreach.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.contactDevParentLayout.setOnClickListener {
            AppNavDirections(findNavController()).settingsToCommonWebView("Contact")
        }

        binding.openSourceParentLayout.setOnClickListener {
            AppNavDirections(findNavController()).settingsToCommonWebView("Developer")
        }
    }
}