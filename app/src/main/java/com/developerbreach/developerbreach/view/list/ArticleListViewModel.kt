package com.developerbreach.developerbreach.view.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.Categories
import com.developerbreach.developerbreach.repository.AppRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class ArticleListViewModel constructor(
    application: Application
) : AndroidViewModel(application) {

    private val repository = AppRepository(getDatabaseInstance(application.applicationContext))
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>>
        get() = _categories

    init {
        viewModelScope.launch {
            val articlesData: List<Article> = repository.getArticlesData()
            _articles.postValue(articlesData)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}