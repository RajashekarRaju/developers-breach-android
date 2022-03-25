package com.developerbreach.developerbreach.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.networkManager.DataState
import com.developerbreach.developerbreach.repository.network.NetworkRepository
import kotlinx.coroutines.*
import timber.log.Timber


class HomeViewModel(
    application: Application,
    private val repository: NetworkRepository
) : AndroidViewModel(application) {

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     *  -> private var viewModelJob = SupervisorJob()
     *
     * This is the main scope for all coroutines launched by HomeViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by viewModelScope by calling viewModelJob.cancel()
     *  -> private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)
     *
     *  Replaced the Job and Scope with viewModelScope.
     *  From v2.1.0 viewModels can launch within scope directly calling viewModelScope
     */

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _state = MutableLiveData<DataState>()
    val articleDataState: LiveData<DataState>
        get() = _state

    private val totalPostsToDoRunQueryOn = 5

    init {
        launchToLoadArticleData()
    }

    /**
     * Operation that cannot be done in the Main Thread
     */
    private fun launchToLoadArticleData() {

        viewModelScope.launch {
            _state.value = DataState.LOADING
            try {
                val articlesData = repository.getArticlesData(totalPostsToDoRunQueryOn)
                _articles.postValue(articlesData)
                _state.value = DataState.DONE
            } catch (e: Exception) {
                Timber.e("Exception caught in HomeViewModel ${e.message}")
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
                    if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                        return HomeViewModel(application, repository) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for HomeViewModel class")
                }
            }
        }
    }
}