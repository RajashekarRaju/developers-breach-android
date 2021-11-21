package com.developerbreach.developerbreach.view.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.repository.AppRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance
import com.developerbreach.developerbreach.utils.DataState
import kotlinx.coroutines.*
import timber.log.Timber

class HomeViewModel(
    application: Application
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

    private val articleDatabase = getDatabaseInstance(application.applicationContext)
    private val repository = AppRepository(articleDatabase)

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _state = MutableLiveData<DataState>()
    val articleDataState: LiveData<DataState>
        get() = _state

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
                val articlesData: List<Article> = repository.getArticlesData()
                _articles.postValue(articlesData)
                _state.value = DataState.DONE
            } catch (e: Exception) {
                Timber.e("Exception caught in HomeViewModel ${e.message}")
                _state.value = DataState.ERROR
            }
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     *
     *      override fun onCleared() {
     *          super.onCleared()
     *          viewModelScope.cancel()
     *      }
     */
}