package com.developerbreach.developerbreach.view.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory

class FavoritesViewModelFactory
/**
 * Creates a [ViewModelProvider.AndroidViewModelFactory]
 *
 * @param application parameter to pass in [AndroidViewModel]
 */
internal constructor(
        private val application: Application
) : AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(application) as T
        }
        throw IllegalArgumentException("Cannot create Instance for FavoritesViewModel class")
    }
}