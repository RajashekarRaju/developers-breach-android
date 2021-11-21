package com.developerbreach.developerbreach.view.intro

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developerbreach.developerbreach.model.Intro
import com.developerbreach.developerbreach.repository.local.LocalRepository


class IntroViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LocalRepository(application.applicationContext)

    private var _introList: List<Intro> = ArrayList()
    val introPagerListData: List<Intro>
        get() = _introList

    init {
        _introList = repository.introPagerListData()
    }
}