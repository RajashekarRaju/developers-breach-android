package com.developerbreach.developerbreach.view.options

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developerbreach.developerbreach.model.Options
import com.developerbreach.developerbreach.repository.local.LocalRepository

class OptionsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = LocalRepository(application.applicationContext)

    private var _optionsList: List<Options> = ArrayList()
    val optionsList: List<Options>
        get() = _optionsList

    init {
        _optionsList = repository.optionsList()
    }
}