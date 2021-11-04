package com.developerbreach.developerbreach.view.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.ArticleDetail
import com.developerbreach.developerbreach.repository.AppRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class ArticleDetailViewModel(
    application: Application,
    val articleId: Int
) : AndroidViewModel(application) {

    private val repository = AppRepository(getDatabaseInstance(application))
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _authorData = MutableLiveData<Pair<String, String>>()
    val authorData: LiveData<Pair<String, String>>
        get() = _authorData

    private val _articleDetailData = MutableLiveData<ArticleDetail>()
    val articleDetailData: LiveData<ArticleDetail>
        get() = _articleDetailData

    private lateinit var articleData: ArticleDetail

    init {
        viewModelScope.launch {
            articleData = repository.getArticlesDetailData(articleId)
            _articleDetailData.postValue(articleData)
            _authorData.postValue(repository.getAuthorDataById(articleData.authorId))
        }
    }

    fun insertFavorite() {
        viewModelScope.launch {
            repository.insertArticleToFavorites(
                Article(
                    articleData.articleId,
                    articleData.name,
                    articleData.banner
                )
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}