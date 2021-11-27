package com.developerbreach.developerbreach.model

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil


data class Intro(
    val id: Int,
    val subtitle: String,
    val banner: Drawable?,
    val background: Drawable?
) {

    companion object DiffCallback : DiffUtil.ItemCallback<Intro>() {

        override fun areItemsTheSame(
            oldItem: Intro,
            newItem: Intro
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Intro,
            newItem: Intro
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}