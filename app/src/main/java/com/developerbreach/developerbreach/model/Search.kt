package com.developerbreach.developerbreach.model

import androidx.recyclerview.widget.DiffUtil

data class Search(
    val articleId: Int,
    val name: String,
) {
    companion object DiffCallback : DiffUtil.ItemCallback<Search>() {

        override fun areItemsTheSame(
            oldItem: Search,
            newItem: Search
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Search,
            newItem: Search
        ): Boolean {
            return oldItem.articleId == newItem.articleId
        }
    }
}
