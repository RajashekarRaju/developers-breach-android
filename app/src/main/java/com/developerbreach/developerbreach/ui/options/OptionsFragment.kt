package com.developerbreach.developerbreach.ui.options
//
//import android.app.Dialog
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import com.developerbreach.developerbreach.R
//import com.developerbreach.developerbreach.databinding.FragmentOptionsBinding
//import com.google.android.material.bottomsheet.BottomSheetDialog
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment
//
//class OptionsFragment : BottomSheetDialogFragment() {
//
//    private val viewModel: OptionsViewModel by viewModels()
//
//    override fun getTheme(): Int {
//        return R.style.BottomSheetDialogTheme
//    }
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return BottomSheetDialog(requireContext(), theme)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        // Inflate the layout for this fragment
//        val binding = FragmentOptionsBinding.inflate(inflater, container, false)
//        binding.navController = findNavController()
//        binding.viewModel = viewModel
//        binding.fragment = this
//        binding.lifecycleOwner = this
//        binding.executePendingBindings()
//        return binding.root
//    }
//}