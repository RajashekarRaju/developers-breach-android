package com.developerbreach.developerbreach.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val articleId: Int,
    val name: String,
    val banner: String
) : Parcelable {

    /**
     * Allows the RecyclerView to determine which items have changed when the list of [Article]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem.articleId == newItem.articleId
        }
    }
}