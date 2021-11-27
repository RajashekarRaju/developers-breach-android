package com.developerbreach.developerbreach.view.authors

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.model.Authors
import com.developerbreach.developerbreach.networkManager.DataState
import kotlinx.coroutines.launch
import timber.log.Timber


class AuthorsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = (application as DevelopersBreachApp).networkRepository

    private val _authors = MutableLiveData<List<Authors>>()
    val authors: LiveData<List<Authors>>
        get() = _authors

    private val _state = MutableLiveData<DataState>()
    val authorsDataState: LiveData<DataState>
        get() = _state

    init {
        launchToLoadAuthorsData()
    }

    private fun launchToLoadAuthorsData() {

        viewModelScope.launch {
            _state.value = DataState.LOADING
            try {
                val categoriesData = repository.getAuthorsData()
                _authors.postValue(categoriesData)
                _state.value = DataState.DONE
            } catch (e: Exception) {
                Timber.e("Exception caught in AuthorsViewModel ${e.message}")
                _state.value = DataState.ERROR
            }
        }
    }
}