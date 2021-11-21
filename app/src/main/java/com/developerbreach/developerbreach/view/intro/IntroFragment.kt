package com.developerbreach.developerbreach.view.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.developerbreach.developerbreach.databinding.FragmentIntroBinding
import com.developerbreach.developerbreach.utils.PrefUtils


class IntroFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding
    private val viewModel: IntroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        PrefUtils(requireContext()).preferenceStateSaved()
    }
}