package com.developerbreach.developerbreach.model

import android.content.Context
import com.developerbreach.developerbreach.R

data class Options(
    val optionsId: Int,
    val optionsTitle: String,
    val optionsIcon: Int,
) {

    companion object {

        fun addOptionsData(
            context: Context,
        ): List<Options> = listOf(
            Options(1, context.getString(R.string.item_options_favorites), R.drawable.ic_favorite_add),
            Options(2, context.getString(R.string.item_options_search), R.drawable.ic_search),
            Options(3, context.getString(R.string.item_options_authors), R.drawable.ic_author),
            Options(4, context.getString(R.string.item_options_settings), R.drawable.ic_settings),
            Options(5, context.getString(R.string.item_options_about), R.drawable.ic_about)
        )
    }
}