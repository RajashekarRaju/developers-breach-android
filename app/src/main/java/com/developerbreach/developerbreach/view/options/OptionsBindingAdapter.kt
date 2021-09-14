package com.developerbreach.developerbreach.view.options

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.controller.AppNavDirections
import com.developerbreach.developerbreach.model.Options


@BindingAdapter("bindOptionsListData", "bindOptionsFragmentReference")
fun RecyclerView.setOptionsListData(
    list: List<Options>,
    fragment: OptionsFragment
) {
    val adapter = OptionsAdapter(fragment)
    adapter.submitList(list)
    this.adapter = adapter
}


@BindingAdapter("bindOptionsItemClickListener", "bindOptionsFragmentReference")
fun ConstraintLayout.setOptionsItemClickListener(
    selectedOptionId: Int,
    fragment: OptionsFragment
) {
    val navController = fragment.findNavController()
    this.setOnClickListener {
        when (selectedOptionId) {
            1 -> navOption(AppNavDirections(navController).optionsToFavorites(), fragment)
            2 -> navOption(AppNavDirections(navController).optionsToSearch(), fragment)
            3 -> navOption(AppNavDirections(navController).optionsToAuthors(), fragment)
            4 -> navOption(AppNavDirections(navController).optionsToSettings(), fragment)
            5 -> navOption(AppNavDirections(navController).optionsToIntro(), fragment)
        }
    }
}

fun navOption(direction: Unit, fragment: OptionsFragment) {
    fragment.dismiss()
    return direction
}
