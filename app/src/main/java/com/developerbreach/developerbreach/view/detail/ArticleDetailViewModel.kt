package com.developerbreach.developerbreach.view.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.ArticleDetail
import com.developerbreach.developerbreach.networkManager.DataState
import kotlinx.coroutines.*
import timber.log.Timber


class ArticleDetailViewModel(
    application: Application,
    val articleId: Int?
) : AndroidViewModel(application) {

    private val app = application as DevelopersBreachApp
    private val networkRepository = app.networkRepository
    private val databaseRepository = app.databaseRepository

    private val _authorData = MutableLiveData<Pair<String, String>>()
    val authorData: LiveData<Pair<String, String>>
        get() = _authorData

    private val _articleDetailData = MutableLiveData<ArticleDetail>()
    val articleDetailData: LiveData<ArticleDetail>
        get() = _articleDetailData

    private val _detailState = MutableLiveData<DataState>()
    val detailState: LiveData<DataState>
        get() = _detailState

    private lateinit var articleData: ArticleDetail

    init {
        loadSelectedArticleDetails()
    }

    private fun loadSelectedArticleDetails() {
        viewModelScope.launch {
            _detailState.value = DataState.LOADING
            try {
                articleData = networkRepository.getArticlesDetailData(articleId)
                _articleDetailData.postValue(articleData)
                _authorData.postValue(networkRepository.getAuthorDataById(articleData.authorId))
                _detailState.value = DataState.DONE
            } catch (e: Exception) {
                Timber.e("Exception caught in ArticleDetailViewModel ${e.message}")
                _detailState.value = DataState.ERROR
            }
        }
    }

    fun insertFavorite() {
        viewModelScope.launch {
            databaseRepository.insertArticleToFavorites(
                Article(
                    articleData.articleId,
                    articleData.name,
                    articleData.banner
                )
            )
        }
    }
}