package com.developerbreach.developerbreach.view.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.repository.AppRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    application: Application,
    val article: Article
) : AndroidViewModel(application) {

    private val repository = AppRepository(getDatabaseInstance(application))
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _authorData = MutableLiveData<Pair<String, String>>()
    val authorData: LiveData<Pair<String, String>>
        get() = _authorData

    // val selectedArticle = article

    init {
        viewModelScope.launch {
            _authorData.postValue(repository.getAuthorDataById(article.authorId))
        }
    }

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