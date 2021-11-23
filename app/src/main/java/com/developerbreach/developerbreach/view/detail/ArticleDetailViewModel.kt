package com.developerbreach.developerbreach.view.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.ArticleDetail
import com.developerbreach.developerbreach.repository.AppRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance
import com.developerbreach.developerbreach.utils.DataState
import kotlinx.coroutines.*
import timber.log.Timber


class ArticleDetailViewModel(
    application: Application,
    val articleId: Int
) : AndroidViewModel(application) {

    private val articleDatabase = getDatabaseInstance(application)
    private val repository = AppRepository(articleDatabase)

    private val _authorData = MutableLiveData<Pair<String, String>>()
    val authorData: LiveData<Pair<String, String>>
        get() = _authorData

    private val _articleDetailData = MutableLiveData<ArticleDetail>()
    val articleDetailData: LiveData<ArticleDetail>
        get() = _articleDetailData

    private lateinit var articleData: ArticleDetail

    private val _detailState = MutableLiveData<DataState>()
    val detailState: LiveData<DataState>
        get() = _detailState

    init {
        loadSelectedArticleDetails()
    }

    private fun loadSelectedArticleDetails() {
        viewModelScope.launch {
            _detailState.value = DataState.LOADING
            try {
                articleData = repository.getArticlesDetailData(articleId)
                _articleDetailData.postValue(articleData)
                _authorData.postValue(repository.getAuthorDataById(articleData.authorId))
                _detailState.value = DataState.DONE
            } catch (e: Exception) {
                Timber.e("Exception caught in ArticleDetailViewModel ${e.message}")
                _detailState.value = DataState.ERROR
            }
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
}