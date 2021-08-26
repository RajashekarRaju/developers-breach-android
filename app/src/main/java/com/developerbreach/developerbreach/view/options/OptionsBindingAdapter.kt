package com.developerbreach.developerbreach.view.options

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.model.Options

@BindingAdapter("bindOptionsFragmentReference", "bindOptionsListData")
fun RecyclerView.setOptionsFragmentReference(
    fragment: OptionsFragment,
    optionsList: List<Options>
) {
    val adapter = OptionsAdapter(fragment)
    adapter.submitList(optionsList)
    this.adapter = adapter
}


//@BindingAdapter("bindItemOptionsClickListener")
//fun ConstraintLayout.setItemOptionsClickListener(
//    options: Options,
//) {
//    this.setOnClickListener {

        // when (options.optionsId) {
            // 1 -> AppNavigationDirections().optionsDialogToNewWord(fragment)
            // 2 -> AppNavigationDirections().optionsToContributions(fragment)
            // 3 -> AppNavigationDirections().optionsToSettings(fragment)
            // 4 -> AppNavigationDirections().optionsToAbout(fragment)
        // }
//    }
//}