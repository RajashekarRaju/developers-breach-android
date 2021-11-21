package com.developerbreach.developerbreach.repository.local

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Intro
import com.developerbreach.developerbreach.model.Options


object LocalData {

    fun addIntroData(
        context: Context,
    ): List<Intro> = listOf(
        Intro(
            1,
            context.getString(R.string.intro_pager_first_subtitle_text),
            AppCompatResources.getDrawable(context, R.drawable.ic_logo),
            AppCompatResources.getDrawable(context, R.drawable.ic_one_pager_intro)
        ),
        Intro(
            2,
            context.getString(R.string.intro_pager_second_subtitle_text),
            AppCompatResources.getDrawable(context, R.drawable.ic_mobile_apps),
            AppCompatResources.getDrawable(context, R.drawable.ic_two_pager_intro)
        ),
        Intro(
            3,
            context.getString(R.string.intro_pager_third_subtitle_text),
            AppCompatResources.getDrawable(context, R.drawable.ic_developer_activity),
            AppCompatResources.getDrawable(context, R.drawable.ic_three_pager_intro)
        ),
        Intro(
            4,
            context.getString(R.string.intro_pager_fourth_subtitle_text),
            AppCompatResources.getDrawable(context, R.drawable.ic_content_creation),
            AppCompatResources.getDrawable(context, R.drawable.ic_four_pager_intro)
        )
    )

    fun addOptionsData(
        context: Context,
    ): List<Options> = listOf(
        Options(
            1,
            context.getString(R.string.item_options_favorites),
            R.drawable.ic_favorite_add
        ),
        Options(
            2,
            context.getString(R.string.item_options_search),
            R.drawable.ic_search
        ),
        Options(
            3,
            context.getString(R.string.item_options_authors),
            R.drawable.ic_author
        ),
        Options(
            4,
            context.getString(R.string.item_options_settings),
            R.drawable.ic_settings
        ),
        Options(
            5,
            context.getString(R.string.item_options_about),
            R.drawable.ic_about
        )
    )
}