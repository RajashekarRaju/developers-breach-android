package com.developerbreach.developerbreach.view.intro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding
    private val viewModel: IntroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        with(
            requireContext().getSharedPreferences(
                getString(R.string.preference_intro_result_key),
                Context.MODE_PRIVATE
            ).edit()
        ) {
            putString(
                getString(R.string.preference_intro_status_key),
                getString(R.string.preference_intro_fragment_shown_value)
            )
            commit()
        }
    }
}