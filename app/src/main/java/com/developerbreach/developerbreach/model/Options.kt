package com.developerbreach.developerbreach.model

import androidx.recyclerview.widget.DiffUtil


data class Options(
    val optionsId: Int,
    val optionsTitle: String,
    val optionsIcon: Int,
) {

    companion object DiffCallback : DiffUtil.ItemCallback<Options>() {

        override fun areItemsTheSame(
            oldItem: Options,
            newItem: Options
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Options,
            newItem: Options
        ): Boolean {
            return oldItem.optionsId == newItem.optionsId
        }
    }
}