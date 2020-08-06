package com.developerbreach.developerbreach.view.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.repository.ArticleRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SettingsViewModel internal constructor(
        application: Application
) : AndroidViewModel(application) {

    private val repository = ArticleRepository(getDatabaseInstance(application))
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val _findFavorites = repository.favorites
    val findFavorites: LiveData<List<Article>>
        get() = _findFavorites

    fun deleteAllArticles() {
        viewModelScope.launch {
            repository.deleteAllFavorites()
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            repository.refreshArticles()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}