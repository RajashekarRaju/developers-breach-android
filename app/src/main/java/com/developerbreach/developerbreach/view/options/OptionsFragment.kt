package com.developerbreach.developerbreach.view.options

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.databinding.FragmentOptionsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OptionsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentOptionsBinding
    private val viewModel: OptionsViewModel by viewModels()

    override fun getTheme(): Int {
        return R.style.BottomSheetDialogTheme
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOptionsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clickListener = OptionsAdapter.OnClickListener { options ->
            when (options.optionsId) {
                1 -> navOption(OptionsFragmentDirections.optionsToFavorites())
                2 -> navOption(OptionsFragmentDirections.optionsToSearch())
                3 -> navOption(OptionsFragmentDirections.optionsToAuthors())
                4 -> navOption(OptionsFragmentDirections.optionsToAuthors())
                5 -> navOption(OptionsFragmentDirections.optionsToIntro())
            }
        }

        val adapter = OptionsAdapter(clickListener)
        adapter.submitList(viewModel.optionsList)
        binding.optionsRecyclerView.adapter = adapter
    }

    private fun navOption(direction: NavDirections) {
        this.dismiss()
        findNavController().navigate(direction)
    }
}