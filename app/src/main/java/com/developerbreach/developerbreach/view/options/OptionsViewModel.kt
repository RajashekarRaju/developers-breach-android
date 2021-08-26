package com.developerbreach.developerbreach.view.options

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developerbreach.developerbreach.model.Options
import com.developerbreach.developerbreach.repository.AppRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance

class OptionsViewModel constructor(
    application: Application
) : AndroidViewModel(application) {

    private val repository = AppRepository(getDatabaseInstance(application.applicationContext))

    private var _optionsList: List<Options> = ArrayList()
    val optionsList: List<Options>
        get() = _optionsList

    init {
        _optionsList = repository.optionsList(application.applicationContext)
    }
}