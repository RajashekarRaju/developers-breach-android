package com.developerbreach.developerbreach.view.intro

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developerbreach.developerbreach.model.Intro

class IntroViewModel(application: Application) : AndroidViewModel(application) {

    private var _introListData: List<Intro> = ArrayList()
    var introListData: List<Intro>
        get() = _introListData
        set(value) {
            _introListData = value
        }

    init {
        _introListData = Intro.addIntroData(application.applicationContext)
    }
}