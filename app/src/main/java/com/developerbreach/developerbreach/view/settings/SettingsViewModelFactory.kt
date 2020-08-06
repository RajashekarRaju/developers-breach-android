package com.developerbreach.developerbreach.view.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory

class SettingsViewModelFactory

/**
 * Creates a [ViewModelProvider.AndroidViewModelFactory]
 *
 * @param application parameter to pass in [AndroidViewModel]
 */
internal constructor(
        // Needs to to be passed as parameter for AndroidViewModel class.
        private val application: Application
) : AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(application) as T
        }
        throw IllegalArgumentException("Cannot create Instance for SettingsViewModel class")
    }

}