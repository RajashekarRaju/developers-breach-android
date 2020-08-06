package com.developerbreach.developerbreach.view.intro

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Intro

class IntroViewModel(application: Application) : AndroidViewModel(application) {

    private var _introListData: List<Intro> = ArrayList()
    var introListData: List<Intro>
        get() = _introListData
        set(value) {
            _introListData = value
        }

    init {
        _introListData = addData(application.applicationContext)
    }

    private fun addData(context: Context): List<Intro> = listOf(
            Intro(1,
                    context.getString(R.string.intro_pager_first_title_text),
                    context.getString(R.string.intro_pager_first_subtitle_text),
                    ContextCompat.getDrawable(context, R.drawable.ic_logo)!!,
                    ContextCompat.getDrawable(context, R.drawable.intro_first_gradient)!!
            ),
            Intro(2,
                    context.getString(R.string.intro_pager_second_title_text),
                    context.getString(R.string.intro_pager_second_subtitle_text),
                    ContextCompat.getDrawable(context, R.drawable.ic_feed)!!,
                    ContextCompat.getDrawable(context, R.drawable.intro_second_gradient)!!
            ),
            Intro(3,
                    context.getString(R.string.intro_pager_third_title_text),
                    context.getString(R.string.intro_pager_third_subtitle_text),
                    ContextCompat.getDrawable(context, R.drawable.ic_notifications)!!,
                    ContextCompat.getDrawable(context, R.drawable.intro_third_gradient)!!
            ),
            Intro(4,
                    context.getString(R.string.intro_pager_fourth_title_text),
                    context.getString(R.string.intro_pager_fourth_subtitle_text),
                    ContextCompat.getDrawable(context, R.drawable.ic_storage)!!,
                    ContextCompat.getDrawable(context, R.drawable.intro_fourth_gradient)!!
            )
    )

}