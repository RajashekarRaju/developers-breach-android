package com.developerbreach.developerbreach.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class Authors(
    val authorId: Int,
    val authorName: String,
    val authorAvatarUrl: String
) : Parcelable {

    companion object DiffCallback : DiffUtil.ItemCallback<Authors>() {

        override fun areItemsTheSame(
            oldItem: Authors,
            newItem: Authors
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Authors,
            newItem: Authors
        ): Boolean {
            return oldItem.authorId == newItem.authorId
        }
    }
}
