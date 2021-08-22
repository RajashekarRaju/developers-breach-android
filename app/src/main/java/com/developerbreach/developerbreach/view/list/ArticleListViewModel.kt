package com.developerbreach.developerbreach.view.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.Categories
import com.developerbreach.developerbreach.repository.ArticleRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance
import com.developerbreach.developerbreach.repository.network.isNetworkConnected
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class ArticleListViewModel constructor(
    application: Application
) : AndroidViewModel(application) {

    private val repository = ArticleRepository(getDatabaseInstance(application.applicationContext))
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

//    private val _articles: LiveData<List<Article>> = repository.articles
//    val articles: LiveData<List<Article>>
//        get() = _articles

    private val _isInternetAvailable = MutableLiveData<Boolean>()
    val isInternetAvailable: LiveData<Boolean>
        get() = _isInternetAvailable

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>>
        get() = _categories

    init {
        // refreshArticlesData()
        _isInternetAvailable.value = isNetworkConnected(application.applicationContext)
        //Log.e("ViewModel", "Init")

        viewModelScope.launch {
            val articlesData: List<Article> = repository.getArticlesData()
            _articles.postValue(articlesData)
            //Log.e("ViewModel", "Launch")
        }

        viewModelScope.launch {
            val categoriesData = repository.getCategoriesData()
            _categories.postValue(categoriesData)
        }
    }

    private fun refreshArticlesData() {
        viewModelScope.launch {
            repository.refreshArticles()
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