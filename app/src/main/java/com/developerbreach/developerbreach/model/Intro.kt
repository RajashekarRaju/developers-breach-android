package com.developerbreach.developerbreach.model

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.developerbreach.developerbreach.R


data class Intro(
    val id: Int,
    val subtitle: String,
    val banner: Drawable?,
    val background: Drawable?
) {

    companion object {

        fun addIntroData(
            context: Context,
        ): List<Intro> = listOf(
            Intro(
                1,
                context.getString(R.string.intro_pager_first_subtitle_text),
                ContextCompat.getDrawable(context, R.drawable.ic_logo),
                ContextCompat.getDrawable(context, R.drawable.ic_one_pager_intro)
            ),
            Intro(
                2,
                context.getString(R.string.intro_pager_second_subtitle_text),
                ContextCompat.getDrawable(context, R.drawable.ic_mobile_apps),
                ContextCompat.getDrawable(context, R.drawable.ic_two_pager_intro)
            ),
            Intro(
                3,
                context.getString(R.string.intro_pager_third_subtitle_text),
                ContextCompat.getDrawable(context, R.drawable.ic_developer_activity),
                ContextCompat.getDrawable(context, R.drawable.ic_three_pager_intro)
            ),
            Intro(
                4,
                context.getString(R.string.intro_pager_fourth_subtitle_text),
                ContextCompat.getDrawable(context, R.drawable.ic_content_creation),
                ContextCompat.getDrawable(context, R.drawable.ic_four_pager_intro)
            )
        )
    }
}