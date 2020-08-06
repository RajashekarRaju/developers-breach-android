package com.developerbreach.developerbreach.view.favorites

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

/**
 * @param application provides application context for ViewModel. With this context get reference
 * to other classes like repository.
 */
class FavoritesViewModel internal constructor(
        application: Application
) : AndroidViewModel(application) {

    // Get a new instance from this app repository using application context.
    // From repository call source which has observable favorites list data and assign it.
    private val repository = ArticleRepository(getDatabaseInstance(application.applicationContext))
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val _favorites: LiveData<List<Article>> = repository.favorites
    val favorites: LiveData<List<Article>>
        get() = _favorites

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            repository.deleteSelectedFavorite(article)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}