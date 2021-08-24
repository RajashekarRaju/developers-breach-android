package com.developerbreach.developerbreach.view.intro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding
    private lateinit var viewModel: IntroViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentIntroBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IntroViewModel::class.java)

        val viewPager = IntroViewPagerAdapter(binding.introViewPager)
        viewPager.submitList(viewModel.introListData)
        binding.introViewPager.adapter = viewPager
    }

    override fun onDestroy() {
        super.onDestroy()

        with(requireContext().getSharedPreferences(
                getString(R.string.preference_intro_result_key),
                Context.MODE_PRIVATE).edit()
        ) {
            putString(
                    getString(R.string.preference_intro_status_key),
                    getString(R.string.preference_intro_fragment_shown_value)
            )
            commit()
        }
    }
}