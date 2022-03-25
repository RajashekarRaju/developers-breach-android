package com.developerbreach.developerbreach.ui.options

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.developerbreach.developerbreach.model.Options
import com.developerbreach.developerbreach.repository.local.LocalRepository

class OptionsViewModel(
    application: Application,
    repository: LocalRepository
) : AndroidViewModel(application) {

    private var _optionsList: List<Options> = ArrayList()
    val optionsList: List<Options>
        get() = _optionsList

    init {
        _optionsList = repository.optionsList()
    }

    companion object {

        fun provideFactory(
            application: Application,
            repository: LocalRepository,
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(OptionsViewModel::class.java)) {
                        return OptionsViewModel(application, repository) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for OptionsViewModel class")
                }
            }
        }
    }
}