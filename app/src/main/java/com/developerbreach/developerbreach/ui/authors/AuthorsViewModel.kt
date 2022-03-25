package com.developerbreach.developerbreach.ui.authors

import android.app.Application
import androidx.lifecycle.*
import com.developerbreach.developerbreach.model.Authors
import com.developerbreach.developerbreach.networkManager.DataState
import com.developerbreach.developerbreach.repository.network.NetworkRepository
import kotlinx.coroutines.launch
import timber.log.Timber


class AuthorsViewModel(
    application: Application,
    private val repository: NetworkRepository
) : AndroidViewModel(application) {

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

    companion object {

        fun provideFactory(
            application: Application,
            repository: NetworkRepository,
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(AuthorsViewModel::class.java)) {
                        return AuthorsViewModel(application, repository) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for AuthorsViewModel class")
                }
            }
        }
    }
}