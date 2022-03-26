package com.developerbreach.developerbreach.ui.home

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.developerbreach.developerbreach.model.Article
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

    // Holds the state for values in HomeUiState
    var uiState by mutableStateOf(HomeUiState())
        private set

    private val totalPostsToDoRunQueryOn = 5

    init {
        launchToLoadArticleData()
    }

    /**
     * Operation that cannot be done in the Main Thread
     */
    private fun launchToLoadArticleData() {
        viewModelScope.launch {
            uiState = HomeUiState(isFetchingArticles = true)
            try {
                val articlesList = repository.getArticlesData(totalPostsToDoRunQueryOn)
                uiState = HomeUiState(
                    articleList = articlesList,
                    isFetchingArticles = false
                )
            } catch (e: Exception) {
                Timber.e("Exception caught in HomeViewModel ${e.message}")
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

data class HomeUiState(
    var articleList: List<Article> = emptyList(),
    val isFetchingArticles: Boolean = false,
)