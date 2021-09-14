package com.developerbreach.developerbreach.model

import androidx.recyclerview.widget.DiffUtil

data class Categories(
    val categoryId: Int,
    val categoryName: String
) {

    companion object DiffCallback : DiffUtil.ItemCallback<Categories>() {

        override fun areItemsTheSame(
            oldItem: Categories,
            newItem: Categories
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Categories,
            newItem: Categories
        ): Boolean {
            return oldItem.categoryId == newItem.categoryId
        }
    }
}
