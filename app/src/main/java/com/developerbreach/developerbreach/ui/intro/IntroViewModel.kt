package com.developerbreach.developerbreach.ui.intro

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.model.Intro
import com.developerbreach.developerbreach.repository.local.LocalRepository


class IntroViewModel(
    application: Application,
    repository: LocalRepository
) : AndroidViewModel(application) {

    private var _introList: List<Intro> = ArrayList()
    val introPagerListData: List<Intro>
        get() = _introList

    init {
        _introList = repository.introPagerListData()
    }

    companion object {

        fun provideFactory(
            application: Application,
            repository: LocalRepository,
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(IntroViewModel::class.java)) {
                        return IntroViewModel(application, repository) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for IntroViewModel class")
                }
            }
        }
    }
}