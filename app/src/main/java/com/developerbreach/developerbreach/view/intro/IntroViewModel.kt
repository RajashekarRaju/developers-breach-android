package com.developerbreach.developerbreach.view.intro

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.model.Intro


class IntroViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val localRepository = (application as DevelopersBreachApp).localRepository

    private var _introList: List<Intro> = ArrayList()
    val introPagerListData: List<Intro>
        get() = _introList

    init {
        _introList = localRepository.introPagerListData()
    }
}