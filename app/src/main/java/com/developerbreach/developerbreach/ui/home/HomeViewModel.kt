package com.developerbreach.developerbreach.ui.home

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.Options
import com.developerbreach.developerbreach.repository.local.LocalRepository
import com.developerbreach.developerbreach.repository.network.NetworkRepository
import com.developerbreach.developerbreach.utils.PrefUtils
import kotlinx.coroutines.*
import timber.log.Timber
import java.io.IOException


class HomeViewModel(
    application: Application,
    private val networkRepository: NetworkRepository,
    private val localRepository: LocalRepository
) : AndroidViewModel(application) {

    // Holds the state for values in HomeUiState
    var uiState by mutableStateOf(HomeUiState())
        private set

    var navigateToIntro by mutableStateOf(false)
        private set

    private val totalPostsToDoRunQueryOn = 5

    init {
        findIntroScreenPreferences()

        viewModelScope.launch {
            try {
                launchToLoadArticleData()
            } catch (e: IOException) {
                Timber.e("Exception caught in HomeViewModel ${e.message}")
            }
        }
    }

    fun getOptionsData(): List<Options> {
        return localRepository.optionsList()
    }

    /**
     * Operation that cannot be done in the Main Thread
     */
    private suspend fun launchToLoadArticleData() {
        uiState = HomeUiState(isFetchingArticles = true)
        val articlesList = networkRepository.getArticlesData(totalPostsToDoRunQueryOn)
        uiState = HomeUiState(
            articleList = articlesList,
            isFetchingArticles = false
        )
    }

    private fun findIntroScreenPreferences() {
        val isIntroShown = PrefUtils(getApplication()).checkPreferenceSaveState()
        navigateToIntro = !isIntroShown
    }

    companion object {

        fun provideFactory(
            application: Application,
            networkRepository: NetworkRepository,
            localRepository: LocalRepository
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                        return HomeViewModel(application, networkRepository, localRepository) as T
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