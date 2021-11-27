package com.developerbreach.developerbreach.view.options

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.model.Options

class OptionsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val localRepository = (application as DevelopersBreachApp).localRepository

    private var _optionsList: List<Options> = ArrayList()
    val optionsList: List<Options>
        get() = _optionsList

    init {
        _optionsList = localRepository.optionsList()
    }
}