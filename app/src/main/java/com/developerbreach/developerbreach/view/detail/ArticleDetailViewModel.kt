package com.developerbreach.developerbreach.view.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.repository.ArticleRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
        application: Application,
        article: Article
) : AndroidViewModel(application) {

    private val repository = ArticleRepository(getDatabaseInstance(application))
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val selectedArticle = article

    fun insertFavorite(article: Article) {
        viewModelScope.launch {
            repository.insertArticle(article)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}